# 🍔 GoGoFood – Food Delivery App

**GoGoFood** is a modern, multi-role food delivery application designed to connect customers, restaurants, and drivers on a unified platform. It provides a seamless ordering and delivery experience with real-time tracking, role-specific interfaces, and a microservice-based backend architecture.

📄 **[📘 Full Specification Document (Google Docs)](https://docs.google.com/document/d/10to7R8pKmIlqJIzjFTqDZrrXIzEHq1eTMEEoiog3Z2I/edit?hl=vi&tab=t.0#heading=h.ug3o61ffdhx1)**

---

## 🚀 Features

### 🧑‍🍳 For Customers:
- Account registration & login
- Browse and search for food
- Place orders and pay online
- Track order status in real time
- Rate restaurants and delivery drivers

### 🏪 For Restaurants:
- Menu management (add, edit, delete items)
- Receive and manage orders
- Update order status (preparing, ready)
- View customer ratings and feedback

### 🛵 For Drivers:
- Receive delivery requests
- Update delivery status (picked up, delivering)
- Navigate using built-in map
- View delivery history

### 🛠️ For Admin:
- Manage users, restaurants, drivers
- View system-wide statistics
- Handle complaints and reports

---

## 🧱 Tech Stack

| Layer          | Technology                     |
|----------------|-------------------------------|
| Frontend       | Flutter (iOS & Android)       |
| Backend        | Spring Boot (Microservices)   |
| Database       | MySQL                         |
| Real-Time      | Firebase / WebSocket          |
| Maps           | Google Maps API               |
| Authentication | JWT, OAuth2                   |
| Media Storage  | Cloudinary                    |
| Payment        | Momo, VNPAY, Cash on Delivery |

---

## 🧑‍💻 Architecture

The project follows a **Microservice Architecture**, separating concerns such as:
- Authentication Service
- Order Management Service
- Delivery Tracking Service
- Restaurant Service
- Admin & Reporting Service

Each microservice communicates via RESTful APIs and is deployed independently for scalability.

---

## 📸 Screenshots

> _Coming Soon_ – Screenshots and mockups designed using Figma.

---

## 📌 Project Status

- [x] UI/UX Design (Figma)
- [x] Database & ERD
- [x] Use Case & Flow Diagrams
- [ ] Implementation in progress

---

## 📬 Contact

For any questions or feedback, please contact:
- **Project Owner**: Lê Duy Khang
- **Email**: ldkhang0410@gmail.com
- **Document**: [GoGoFood Full Document](https://docs.google.com/document/d/10to7R8pKmIlqJIzjFTqDZrrXIzEHq1eTMEEoiog3Z2I/edit?hl=vi&tab=t.0#heading=h.ug3o61ffdhx1)

---

## 📄 License

This project is licensed under the [MIT License](LICENSE).
