import 'package:flutter/material.dart';

class SignupViewModel extends ChangeNotifier {
  final fullNameController = TextEditingController();
  final emailPhoneControler = TextEditingController();
  final passwordController = TextEditingController();
  final confirmPasswordController = TextEditingController();

  bool showPassword = false;
  bool showConfirmPassword = false;

  String? fullNameError;
  String? emailPhoneError;
  String? passwordError;
  String? confirmPasswordError;

  void togglePasswordVisibility() {
    showPassword = !showPassword;
    notifyListeners();
  }

  void toggleConfirmPasswordVisibility() {
    showConfirmPassword = !showConfirmPassword;
    notifyListeners();
  }

  void signup() {}

  void login() {}

  void signInWithGoogle() {}

  void signInWithFacebook() {}

  void dispose() {
    fullNameController.dispose();
    emailPhoneControler.dispose();
    passwordController.dispose();
    confirmPasswordController.dispose();
    super.dispose();
  }

  bool _validate() {
    bool isValid = true;

    fullNameError = fullNameController.text.isEmpty
        ? 'Full name is required'
        : null;
    emailPhoneError = fullNameController.text.isEmpty
        ? 'Phone or email is required'
        : null;
    passwordError = passwordController.text.isEmpty
        ? 'Password is required'
        : null;

    if (confirmPasswordController.text.isEmpty) {
      confirmPasswordError = 'Confirm password is required';
    } else if (confirmPasswordController.text != passwordController.text) {
      confirmPasswordError = 'Password do not match';
    } else {
      confirmPasswordError = null;
    }

    if (fullNameError != null ||
        emailPhoneError != null ||
        passwordError != null ||
        confirmPasswordError != null) {
      isValid = false;
    }

    notifyListeners();
    return isValid;
  }
}
