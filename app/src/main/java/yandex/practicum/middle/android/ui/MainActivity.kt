package yandex.practicum.middle.android.ui


import android.content.ComponentName
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.mutableStateOf
import androidx.concurrent.futures.await
import androidx.lifecycle.lifecycleScope
import androidx.media3.session.MediaController
import androidx.media3.session.SessionToken
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    private var mediaController = mutableStateOf<MediaController?>(null)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val url = "asset:/audio.mp3"
        setContent {
            MaterialTheme {
                val player = mediaController.value
                if (player != null)
                    PlayerScreen(player, url)
            }
        }
    }

    override fun onStart() {
        super.onStart()

        val sessionToken = SessionToken(this, ComponentName(this, PlaybackService::class.java))
        lifecycleScope.launch {
            mediaController.value = MediaController.Builder(this@MainActivity, sessionToken).buildAsync().await()
        }
    }

    override fun onStop() {
        super.onStop()

        mediaController.value?.release()
        mediaController.value = null
    }
}
