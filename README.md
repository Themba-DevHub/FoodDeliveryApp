# Food Delivery App

A robust, file-based Food Delivery Application built with Java. This project demonstrates a clean, layered architecture separating models, services, and repositories, simulating a real-world food ordering system.

## 🚀 Features

*   **Menu Management**: Browse and manage menu items.
*   **Order Processing**: Create, track, and manage customer orders.
*   **File Persistence**: Data is persisted using a custom file-based repository system, properly simulating database operations.
*   **Notification System**: Service layer for handling order updates and notifications.
*   **Clean Architecture**: Structured with clear separation of concerns (Models, Services, Repositories).

## 🛠️ Tech Stack

*   **Language**: Java
*   **IDE**: NetBeans
*   **Persistence**: File I/O (Custom Implementation)

## 📂 Project Structure

```text
src/fooddeliveryapp
├── model/          # Data entities (Order, Customer, MenuItem)
├── repository/     # Data access layer (File-based implementation)
├── service/        # Business logic (OrderService, MenuService)
└── util/           # Utility classes (Validation, formatting)
```

## 🔧 How to Run

1.  Clone the repository:
    ```bash
    git clone https://github.com/Themba-DevHub/FoodDeliveryApp.git
    ```
2.  Open the project in **NetBeans** (or any Java IDE).
3.  Build the project to resolve dependencies.
4.  Run the main application class.

## 🤝 Contributing

Contributions are welcome! Please feel free to submit a Pull Request.
