package com.jodonnel.parkinsons.memory

import android.content.Context
import androidx.core.content.edit
import org.json.JSONArray
import org.json.JSONObject

/**
 * Local on-device store for personally important names and words.
 *
 * Privacy: all data stays on the device. Nothing is transmitted.
 * Structure: each entry has a word/name and optional context tags.
 *
 * Example entries:
 *   { "word": "Goethe", "tags": ["author", "german"] }
 *   { "word": "Moda", "tags": ["medication", "parkinson"] }
 */
class MemoryStore(context: Context) {

    private val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    data class Entry(val word: String, val tags: List<String>)

    fun add(word: String, tags: List<String> = emptyList()) {
        val entries = loadAll().toMutableList()
        if (entries.none { it.word.equals(word, ignoreCase = true) }) {
            entries.add(Entry(word, tags))
            save(entries)
        }
    }

    fun remove(word: String) {
        val entries = loadAll().filter { !it.word.equals(word, ignoreCase = true) }
        save(entries)
    }

    fun search(query: String): List<Entry> {
        val q = query.lowercase()
        return loadAll().filter { entry ->
            entry.word.lowercase().contains(q) ||
            entry.tags.any { it.lowercase().contains(q) }
        }
    }

    fun loadAll(): List<Entry> {
        val raw = prefs.getString(KEY_ENTRIES, "[]") ?: "[]"
        val arr = JSONArray(raw)
        return (0 until arr.length()).map { i ->
            val obj = arr.getJSONObject(i)
            val tags = obj.optJSONArray("tags")?.let { t ->
                (0 until t.length()).map { t.getString(it) }
            } ?: emptyList()
            Entry(obj.getString("word"), tags)
        }
    }

    private fun save(entries: List<Entry>) {
        val arr = JSONArray()
        entries.forEach { entry ->
            val obj = JSONObject()
            obj.put("word", entry.word)
            val tags = JSONArray()
            entry.tags.forEach { tags.put(it) }
            obj.put("tags", tags)
            arr.put(obj)
        }
        prefs.edit { putString(KEY_ENTRIES, arr.toString()) }
    }

    companion object {
        private const val PREFS_NAME = "memory_store"
        private const val KEY_ENTRIES = "entries"
    }
}