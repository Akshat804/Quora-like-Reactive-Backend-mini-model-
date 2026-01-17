# 🚀 Quora Reactive Backend (Mini System Design Project)

A **reactive backend system inspired by Quora**, built using **Spring WebFlux**, **MongoDB**, **Elasticsearch**, and **Kafka**, focusing on **scalability, non-blocking I/O, and event-driven architecture**.

This project demonstrates how modern backend systems handle **search, view counting, and asynchronous processing** efficiently.

---

## ✨ Key Features

### 📝 Question Management
- Create and fetch questions
- MongoDB used as the **primary data store**
- Reactive CRUD operations using WebFlux

### 🔍 Full-Text Search with Elasticsearch
- Questions indexed in Elasticsearch
- Search by title and content
- MongoDB remains the source of truth
- Elasticsearch optimized for fast text search

### ⚡ Reactive & Non-Blocking APIs
- Built using **Spring WebFlux**
- Uses `Mono` and `Flux`
- Backpressure-aware streaming
- High scalability under concurrent requests

### 📊 View Count with Kafka (Event-Driven)
- Searching a question by ID publishes a Kafka event
- Kafka consumer asynchronously updates view count
- Request latency is not affected by DB updates
- Demonstrates **eventual consistency**

### 📨 Queue-Based Architecture
- Kafka decouples read operations from write-heavy updates
- Improves system responsiveness
- Handles high read traffic safely

### 📄 Cursor-Based Pagination
- Efficient pagination for large datasets
- Avoids duplicate or missing records
- Uses timestamps instead of offsets

---

## 🧠 System Architecture

