# Parkinson's Memory Coach

**Real-time word recall and safety support for people with Parkinson's — delivered through Meta Ray-Ban smart glasses.**

Voice-only. Zero screen. Always listening when it matters.

---

## Why This Exists

People with Parkinson's frequently lose access to words and names mid-conversation, especially when tired. They also face sudden safety risks — freezing, falls, disorientation — that current tools don't catch in the moment.

This project puts support directly in the ear using glasses that already look like normal sunglasses. No phone. No wake word. No extra hardware.

## What It Does (v0)

- **Proactive word recall** — Detects when the wearer is stuck on a name or word and supplies it naturally.
- **Safety detection** — Uses the glasses camera to notice unsafe situations and gives immediate audio alerts.
- **Passive listening** — No explicit commands required. The system only speaks when it detects genuine need.

## Primary User

Jim O'Donnell's father. Parkinson's with memory loss. Uses a Samsung Galaxy Z Flip. Has a Hero smart pill dispenser.

## Form Factor

Meta Ray-Ban smart glasses (bone conduction + camera + mic). The phone acts only as a silent relay. The wearer never touches a screen.

## Current State

The Android multi-module scaffold is in place:

| Module     | Purpose                              |
|------------|--------------------------------------|
| `wearable` | Meta DAT SDK integration             |
| `stt`      | On-device SpeechRecognizer           |
| `core`     | CloudEvents model + network client   |
| `app`      | Main application shell               |

Next focus: passive inference for word-recall triggers and basic camera-based safety detection.

## Contributing

This is a public development repo. Issues and pull requests are welcome — especially from people living with Parkinson's or supporting someone who is.

## License

Apache 2.0

---

Originally based on the Job Coach project. This repo narrows the scope to memory support and safety for Parkinson's.