# Parkinson's Memory Coach

Real-time word recall + safety support for people with Parkinson's, delivered through Meta Ray-Ban smart glasses. Voice-only, zero screen, always listening.

## The Problem

People with Parkinson's often lose access to proper names and words mid-conversation, especially when tired. They also face real safety risks (freezing, falls, disorientation) that current technology doesn't address in the moment.

Current solutions require looking at a phone or asking for help. This project puts support directly in the ear via glasses that already look normal.

## What It Does (v0)

- **Proactive word recall** — Detects when the wearer is stuck on a name or word and supplies it without being explicitly asked.
- **Safety detection** — Uses the glasses camera to notice unsafe situations (fall risk, getting lost, freezing, hazards) and gives immediate audio alerts.
- **Passive listening** — No wake word. The system listens and only speaks when it detects genuine need.

## Primary User

Jim O'Donnell's father. Parkinson's with memory loss. Uses a Samsung Galaxy Z Flip. Has a Hero pill dispenser.

## Form Factor

Meta Ray-Ban smart glasses (bone conduction audio + camera + mic). The phone is only a background relay. The wearer never interacts with a screen.

## Current State

Multi-module Android scaffold exists with:
- Meta DAT SDK integration (wearable module)
- On-device SpeechRecognizer (stt module)
- CloudEvents model + OpenShift client (core module)
- Basic app shell

Next work is focused on passive inference for word-recall triggers and basic camera-based safety detection.

## Repo

This is the public development repo. Issues and contributions welcome, especially from people living with Parkinson's or caring for someone who is.

License: Apache 2.0

## Related

Originally forked from the Job Coach project (https://github.com/jodonnel/job-coach). This repo narrows the scope to memory support + safety for Parkinson's.