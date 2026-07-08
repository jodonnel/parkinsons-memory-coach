package com.jodonnel.parkinsons.recall

/**
 * Detects hesitation patterns in a speech transcript that suggest the speaker
 * is struggling to find a word or name.
 *
 * Patterns detected:
 *   - Filler words/sounds: "um", "uh", "er", "hmm"
 *   - Circumlocution: "what's the word", "I can't remember", "the thing", "you know"
 *   - Trailing off: sentence ends abruptly mid-thought (short final word after long sentence)
 *
 * Usage:
 *   val detector = HesitationDetector()
 *   if (detector.detect(transcript)) {
 *     // trigger word recall lookup
 *   }
 */
class HesitationDetector {

    data class Detection(
        val detected: Boolean,
        val reason: String = ""
    )

    private val fillers = setOf(
        "um", "uh", "er", "hmm", "erm", "ah", "like"
    )

    private val circumlocutions = listOf(
        "what's the word",
        "what is the word",
        "i can't remember",
        "i can't think",
        "the thing",
        "you know",
        "what do you call",
        "what do you call it",
        "how do you say",
        "it's on the tip of my tongue",
        "i forget",
        "i forgot"
    )

    fun detect(transcript: String): Detection {
        val lower = transcript.lowercase().trim()
        val words = lower.split(Regex("\\s+"))

        // Check filler words — two or more fillers in a short span
        val fillerCount = words.count { it in fillers }
        if (fillerCount >= 2) {
            return Detection(true, "Multiple filler words detected ($fillerCount)")
        }

        // Check circumlocution phrases
        circumlocutions.forEach { phrase ->
            if (lower.contains(phrase)) {
                return Detection(true, "Circumlocution: \"$phrase\"")
            }
        }

        // Check for single filler at end of utterance (trailing off)
        if (words.isNotEmpty() && words.last() in fillers) {
            return Detection(true, "Trailing filler: \"${words.last()}\"")
        }

        return Detection(false)
    }
}