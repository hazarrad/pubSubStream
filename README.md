# Google Pub/Sub Example

This project is a Spring Boot application that publishes and receives messages using **Google Cloud Pub/Sub**.  
It contains:

- A REST endpoint for sending messages to a Pub/Sub topic  
- A Pub/Sub subscriber that listens for new messages  
- Configuration that loads Google service account credentials  
- Properties for connecting to GCP resources  

The project uses **Spring Boot**, **Google Pub/Sub Java Client**, and **Configuration Properties**.

---

### 🚀 How It Works

POST /pub?message=Hello

#### ✔ Publish Message  
You can send a message to Pub/Sub using:


The app will publish the message to the configured GCP topic.

---

#### ✔ Receive Message  
The subscriber starts automatically when the application runs.  
Whenever a message arrives in the subscription, the app prints it to the logs.

---

## 🧩 Project Structure

### **1. PropertiesConfig**  
Reads the GCP configurations from `application.yml`.

```yaml
gcp:
  resourcesFolder: classpath:
  bucketName: your-bucket
  key: service-account.json
  projectId: your-project-id
  topicId: your-topic
  subscription: your-subscription
```

---
### 🔐 Service Account File

Place your service account file in: src/main/resources/key.json

⚠ Note: The service account file should never be committed to your project repository.
It must be stored securely, preferably in a secret storage or environment-protected location.

Make sure the key in application.yml matches the filename.

---
### Pub/Sub Beans
✔ GoogleCredentials

Loads the service account file and creates credentials.

✔ Publisher

Creates a Pub/Sub publisher that sends messages to the topic.

✔ Subscriber

Starts listening to incoming messages from the subscription.
Whenever a message arrives, it logs the content and acknowledges it.

---

### ▶️ Running the Application

Add your service account JSON file to src/main/resources

Update your application.yml with your GCP values

Run:

```bash
$ mvn spring-boot:run
```
When the app starts, you will see logs like:

Starting Pub/Sub subscriber…
Subscriber started for: my-subscription


---
### 🧪 Unit Tests

The project includes tests for:

- Publishing messages  
- Starting the subscriber  

Mocks are used so Pub/Sub is not called for real.

---
### 📬 Publish a Test Message

Use Postman, curl, or browser to send a test message:

```bash
http://localhost:8080/pub?message=Hello%20World
```

You should see in logs:

```bash
Published message ID: 123456
```


