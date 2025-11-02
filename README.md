# TaskMate Smart 📱

A modern, intelligent task management application built with **Jetpack Compose**, **Firebase Authentication**, and **Material 3 Design**. TaskMate Smart helps you organize, track, and complete your tasks efficiently with a beautiful, intuitive interface.

![Android](https://img.shields.io/badge/Android-3DDC84?style=for-the-badge&logo=android&logoColor=white)
![Kotlin](https://img.shields.io/badge/kotlin-%237F52FF.svg?style=for-the-badge&logo=kotlin&logoColor=white)
![Firebase](https://img.shields.io/badge/Firebase-039BE5?style=for-the-badge&logo=Firebase&logoColor=white)
![Jetpack Compose](https://img.shields.io/badge/Jetpack%20Compose-4285F4?style=for-the-badge&logo=jetpackcompose&logoColor=white)

## ✨ Features

### 🔐 **Authentication**
- **Email/Password** authentication with Firebase
- **Google Sign-In** integration with fallback support
- Secure session management
- Auto-navigation based on authentication state
- Beautiful login/register screens with Material 3 design

### 🎨 **User Interface**
- **Material 3 Design System** with dynamic theming
- **Jetpack Compose** for modern, declarative UI
- Responsive layouts for different screen sizes
- Smooth animations and transitions
- Loading states and error handling

### 🏗️ **Architecture**
- **MVVM Pattern** (Model-View-ViewModel)
- **Repository Pattern** for data management
- **Dependency Injection** ready with Hilt/Dagger (planned)
- **State Management** with Compose State and StateFlow
- Clean separation of concerns

## 🚀 Getting Started

### Prerequisites
- **Android Studio** (latest stable version)
- **Android SDK** (API level 24+)
- **Google account** for Firebase setup
- **Git** for version control

### 🔧 Installation

1. **Clone the repository**
   ```bash
   git clone https://github.com/your-username/taskmate-smart.git
   cd taskmate-smart
   ```

2. **Open in Android Studio**
   - Open Android Studio
   - Choose "Open an existing project"
   - Navigate to the cloned directory and open it

3. **Sync Gradle files**
   ```bash
   ./gradlew build
   ```

### 🔥 Firebase Setup

#### 1. Create Firebase Project
1. Go to [Firebase Console](https://console.firebase.google.com/)
2. Click "Create a project" or "Add project"
3. Enter project name: `TaskMate Smart`
4. Enable Google Analytics (optional)
5. Click "Create project"

#### 2. Add Android App
1. In Firebase console, click "Add app" → **Android**
2. Enter package name: `com.siriha.taskmatesmart`
3. Enter app nickname: `TaskMate Smart`
4. Download `google-services.json`
5. Place the file in the `app/` directory

#### 3. Get SHA-1 Fingerprint
```bash
./gradlew signingReport
```
Copy the SHA-1 fingerprint from the debug variant.

#### 4. Configure Firebase Authentication
1. Go to **Authentication** → **Sign-in method**
2. Enable **Email/Password** provider
3. Enable **Google** provider
4. Add your SHA-1 fingerprint in **Project Settings**
5. Copy the **Web client ID** from Google provider settings

#### 5. Update Configuration
Open `app/src/main/java/com/siriha/taskmatesmart/utils/Constants.kt`:
```kotlin
const val WEB_CLIENT_ID = "your-actual-web-client-id.apps.googleusercontent.com"
```

#### 6. Build and Run
```bash
./gradlew clean build
```

## 📱 Usage

### Authentication Flow
1. **Launch App** → AuthActivity (Login/Register screens)
2. **Choose Authentication Method:**
   - Email/Password with form validation
   - Google Sign-In with one-tap experience
3. **Successful Login** → MainActivity (Welcome screen)
4. **Sign Out** → Returns to AuthActivity

### Current Features
- ✅ **Email Registration** with validation
- ✅ **Email Login** with error handling
- ✅ **Google Sign-In** with fallback support
- ✅ **Session Management** with auto-navigation
- ✅ **Sign Out** functionality
- ✅ **Loading States** and user feedback

### Upcoming Features
- 📝 **Task Creation** and editing
- 📋 **Task Lists** with categories
- 🏷️ **Tags and Priorities**
- 📅 **Due Dates** and reminders
- 🔄 **Sync** across devices
- 📊 **Analytics** and insights

## 🏗️ Project Structure

```
app/src/main/java/com/siriha/taskmatesmart/
├── 📱 AuthActivity.kt                    # Authentication flow entry point
├── 📱 MainActivity.kt                    # Main app after authentication
├── 📁 data/
│   ├── 📁 model/
│   │   └── 📄 Task.kt                   # Task data model
│   └── 📁 repository/
│       ├── 📄 AuthRepository.kt         # Firebase Auth operations
│       └── 📄 TaskRepository.kt         # Task CRUD operations
├── 📁 di/
│   └── 📄 AppModule.kt                  # Dependency injection
├── 📁 ui/
│   ├── 📁 components/
│   │   └── 📄 TaskItem.kt              # Reusable UI components
│   ├── 📁 navigation/
│   │   └── 📄 AuthNavigation.kt        # Navigation logic
│   ├── 📁 screens/
│   │   ├── 📄 LoginScreen.kt           # Login UI
│   │   └── 📄 RegisterScreen.kt        # Registration UI
│   └── 📁 theme/
│       ├── 📄 Color.kt                 # Material 3 colors
│       ├── 📄 Theme.kt                 # App theming
│       └── 📄 Type.kt                  # Typography
├── 📁 utils/
│   ├── 📄 AuthErrorHandler.kt          # Error handling utilities
│   ├── 📄 Constants.kt                 # App constants
│   ├── 📄 GoogleSignInHelper.kt        # Google Sign-In utilities
│   └── 📄 ResultState.kt               # State management
└── 📁 viewmodel/
    └── 📄 AuthViewModel.kt             # Authentication state management
```

## 🛠️ Tech Stack

### **Frontend**
- **Jetpack Compose** - Modern Android UI toolkit
- **Material 3** - Google's latest design system
- **Navigation Compose** - Type-safe navigation
- **ViewModel** - UI state management
- **StateFlow** - Reactive data streams

### **Backend & Services**
- **Firebase Authentication** - User authentication
- **Firebase Firestore** - NoSQL database (planned)
- **Firebase Cloud Messaging** - Push notifications (planned)

### **Architecture & Tools**
- **MVVM** - Model-View-ViewModel pattern
- **Repository Pattern** - Data layer abstraction
- **Coroutines** - Asynchronous programming
- **Hilt/Dagger** - Dependency injection (planned)
- **Gradle** - Build system

### **Authentication**
- **Email/Password** - Traditional authentication
- **Google Sign-In** - OAuth with Google
- **Credential Manager** - Modern credential handling
- **Biometric Auth** - Fingerprint/Face ID (planned)

## 🔍 Troubleshooting

### Common Issues

#### Google Sign-In Not Working
**Symptoms:** "No credentials available" error
**Solutions:**
1. Verify SHA-1 fingerprint is added to Firebase
2. Check Web Client ID in `Constants.kt`
3. Ensure Google provider is enabled in Firebase Auth
4. Update `google-services.json` after adding SHA-1

#### Build Errors
**Solutions:**
1. Clean and rebuild: `./gradlew clean build`
2. Invalidate caches: **File** → **Invalidate Caches and Restart**
3. Check Gradle sync status
4. Verify all dependencies are up to date

#### Authentication Stuck Loading
**Solutions:**
1. Check internet connection
2. Verify Firebase project configuration
3. Check Android Studio logs for specific errors
4. Ensure proper threading (fixed in latest version)

### Debug Commands
```bash
# Get signing report with SHA-1
./gradlew signingReport

# Clean build
./gradlew clean

# Build debug APK
./gradlew assembleDebug

# Run tests
./gradlew test

# Check dependencies
./gradlew dependencies
```

## 📊 Development Status

### ✅ **Completed**
- [x] Firebase Authentication setup
- [x] Email/Password authentication
- [x] Google Sign-In integration
- [x] Material 3 UI design
- [x] MVVM architecture
- [x] State management
- [x] Error handling
- [x] Navigation flow

### 🚧 **In Progress**
- [ ] Task management features
- [ ] User profile management
- [ ] Data persistence

### 📋 **Planned**
- [ ] Task categories and tags
- [ ] Push notifications
- [ ] Offline sync
- [ ] Data export/import
- [ ] Advanced search and filtering
- [ ] Collaboration features
- [ ] Analytics and insights

## 🤝 Contributing

1. **Fork the repository**
2. **Create a feature branch**: `git checkout -b feature/amazing-feature`
3. **Commit changes**: `git commit -m 'Add amazing feature'`
4. **Push to branch**: `git push origin feature/amazing-feature`
5. **Open a Pull Request**

### Development Guidelines
- Follow **Material 3** design principles
- Write **clean, documented code**
- Add **unit tests** for new features
- Follow **MVVM architecture** patterns
- Use **meaningful commit messages**

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 📞 Support

- **Issues**: [GitHub Issues](https://github.com/your-username/taskmate-smart/issues)
- **Discussions**: [GitHub Discussions](https://github.com/your-username/taskmate-smart/discussions)
- **Email**: your-email@example.com

## 🙏 Acknowledgments

- **Firebase** team for excellent authentication services
- **Android** team for Jetpack Compose
- **Material Design** team for the beautiful design system
- **Kotlin** team for the amazing programming language

---

**Made with ❤️ using Jetpack Compose and Firebase**

*Last updated: November 2024*