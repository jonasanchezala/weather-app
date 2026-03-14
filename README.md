# Weather Reactive app
This project is a high-performance, reactive Spring Boot application designed to process real-time environmental data via UDP and stream it to an Apache Kafka broker.

## Architecture

The system uses a **Docker Bridge Network** to manage communication between your host and the Linux-based containers:
- **Internal:** The Java app connects to Kafka via `kafka:29092`.
- **External:** You can monitor Kafka from via `localhost:9092`.
- **Ingestion:** Sensors send UDP packets to `localhost` ports `3344` and `3355`, which Docker bridges to the application.
---

## Getting Started

### 1. Prerequisites
- **Docker**.
- **Ncat** (Standard for testing UDP) or any other tool.

### 2. Deployment
To ensure a clean environment and wipe old Kafka metadata, run:

```bash
docker compose down -v && docker compose up --build
```
---

## Sensor Simulation

Use the following commands from your terminal to simulate sensor data.

### Temperature Sensor
- **UDP Port:** `3344`
- **Threshold:** Alert triggers if `value > 35`
- **Command (Ncat):**
  ```bash
  echo -n "sensor_id=t1; value=30" | nc -u -w1 localhost 3344
  ```

### Humidity Sensor
- **UDP Port:** `3355`
- **Threshold:** Alert triggers if `value > 50`
- **Command (Ncat):**
  ```bash
  echo -n "sensor_id=h1; value=40" | nc -u -w1 localhost 3355
  ```

---

## Verification & Debugging

### Watch Application Logs
Verify that the UDP packets are being received and processed by the Java app:
```bash
docker logs -f weather-app
```
---

## 📊 Technical Specifications

| Component | Detail |
| :--- | :--- |
| **Framework** | Spring Boot 3.x (Reactive Stack) |
| **Kafka Mode** | KRaft (No Zookeeper) |
| **ProtoBuf** | Kafka messages |
