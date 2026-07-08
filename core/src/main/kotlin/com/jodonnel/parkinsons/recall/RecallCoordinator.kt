package com.jodonnel.parkinsons.recall

import android.content.Context
import com.jodonnel.parkinsons.memory.MemoryStore

/**
 * Listens to transcribed speech and proactively supplies words when hesitation is detected.
 *
 * Flow:
 *   1. Receive transcript segment from STT
 *   2. Run HesitationDetector
 *   3. If hesitation detected, search MemoryStore for relevant entry
 *   4. Return suggested word/name to caller for audio output
 *
 * The caller is responsible for speaking the suggestion via bone-conduction audio.
 */
class RecallCoordinator(context: Context) {

    private val detector = HesitationDetector()
    private val store = MemoryStore(context)

    data class Suggestion(
        val triggered: Boolean,
        val word: String = "",
        val reason: String = ""
    )

    fun process(transcript: String): Suggestion {
        val detection = detector.detect(transcript)
        if (!detection.detected) return Suggestion(false)

        // Search memory store for any word related to recent context
        val words = transcript.lowercase().split(Regex("\\s+"))
        for (word in words) {
            val matches = store.search(word)
            if (matches.isNotEmpty()) {
                return Suggestion(
                    triggered = true,
                    word = matches.first().word,
                    reason = detection.reason
                )
            }
        }

        // Hesitation detected but no specific word found in store
        return Suggestion(
            triggered = true,
            word = "",
            reason = detection.reason
        )
    }
}