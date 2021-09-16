package net.vitaldigitalmedia.secretcontacts


import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import com.hextremelabs.pinpad.PinpadView
import net.vitaldigitalmedia.secretcontacts.databinding.ActivityAuthenticationBinding
import java.util.concurrent.Executor

class AuthenticationActivity : AppCompatActivity() {
    private lateinit var executor: Executor
    private lateinit var biometricPrompt: BiometricPrompt
    private lateinit var promptInfo: BiometricPrompt.PromptInfo
    private lateinit var binding: ActivityAuthenticationBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthenticationBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.apply {
            pinpad.viewProvider = pinview
            pinpad.callback = object : PinpadView.Callback {
                override fun onPasscodeComplete(passcode: String) {
                    Toast.makeText(applicationContext, "You typed $passcode", Toast.LENGTH_LONG)
                        .show()
                    startMainActivity()
                }

                override fun onHelpRequest() {
                    Toast.makeText(applicationContext, "Help is coming", Toast.LENGTH_LONG).show()
                }
            }
        }


        executor = ContextCompat.getMainExecutor(this)
        biometricPrompt = BiometricPrompt(this, executor,
            object : BiometricPrompt.AuthenticationCallback() {
                override fun onAuthenticationError(
                    errorCode: Int,
                    errString: CharSequence
                ) {
                    super.onAuthenticationError(errorCode, errString)
                    Toast.makeText(
                        applicationContext,
                        "Authentication error: $errString", Toast.LENGTH_SHORT
                    ).show()
                }

                override fun onAuthenticationSucceeded(
                    result: BiometricPrompt.AuthenticationResult
                ) {
                    super.onAuthenticationSucceeded(result)
                    Toast.makeText(
                        applicationContext,
                        "Authentication succeeded!", Toast.LENGTH_SHORT
                    )
                        .show()
                    startMainActivity()
                }

                override fun onAuthenticationFailed() {
                    super.onAuthenticationFailed()
                    Toast.makeText(
                        applicationContext, "Authentication failed",
                        Toast.LENGTH_SHORT
                    )
                        .show()
                }
            })

        promptInfo = BiometricPrompt.PromptInfo.Builder()
            .setTitle("Biometric login for my app")
            .setSubtitle("Log in using your biometric credential")
            .setNegativeButtonText("Use account password")
            .build()

        // Prompt appears when user clicks "Log in".
        // Consider integrating with the keystore to unlock cryptographic operations,
        // if needed by your app.
        binding.biometricLogin.setOnClickListener {
            biometricPrompt.authenticate(promptInfo)
        }
    }

    private fun startMainActivity() {
        val intent = MainActivity.createIntent(this)
        startActivity(intent)
    }

}