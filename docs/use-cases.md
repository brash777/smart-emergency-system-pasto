# Use Cases

## Use Case 1: Report Emergency

Actor: User

Description:
The user reports an emergency by uploading an image and location.

Steps:
1. User opens the web application
2. User uploads an image
3. User shares location
4. User submits report

Result:
The system processes the data and sends it to the backend.

---

## Use Case 2: Classify Emergency

Actor: System

Description:
The system analyzes the uploaded image using AI.

Steps:
1. Backend sends image to AI service
2. AI analyzes image
3. AI returns classification

Result:
Emergency type is identified (accident, fire, robbery).

---

## Use Case 3: Assign Service

Actor: System

Description:
The system assigns the correct emergency service.

Result:
- Police → robbery
- Ambulance → accident
- Firefighters → fire
