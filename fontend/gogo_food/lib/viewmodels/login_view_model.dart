import 'package:flutter/material.dart';

class LoginViewModel extends ChangeNotifier {
  final usernameController = TextEditingController();
  final passwordController = TextEditingController();

  bool showPassword = false;
  bool isValid = false;

  final usernameFocus = FocusNode();
  final passwordFocus = FocusNode();

  String? usernameError;
  String? passwordError;

  LoginViewModel() {
    usernameFocus.addListener(() {
      if (!usernameFocus.hasFocus) validateUsername();
    });

    passwordFocus.addListener(() {
      if (!passwordFocus.hasFocus) validatePassword();
    });

    usernameController.addListener(() {
      validateUsername();
      _updateValidity();
    });

    passwordController.addListener(() {
      validatePassword();
      _updateValidity();
    });
  }

  void _updateValidity() {
    final isUsernameValid = usernameController.text.trim().isNotEmpty;
    final isPasswordValid = passwordController.text.trim().isNotEmpty;

    isValid = isUsernameValid && isPasswordValid;
    notifyListeners();
  }

  void togglePasswordVisibility() {
    showPassword = !showPassword;
    notifyListeners();
  }

  void forgotPassword() {}

  void login() {}

  void signInWithFacebook() {}

  void signInWithGoogle() {}

  void dispose() {
    usernameController.dispose();
    passwordController.dispose();
    super.dispose();
  }

  bool _validateAll() {
    validateUsername();
    validatePassword();

    isValid = usernameError == null && passwordError == null;

    notifyListeners();
    return isValid;
  }

  void validateUsername() {
    final username = usernameController.text.trim();
    if (username.isEmpty) {
      usernameError = 'Username is required';
    } else {
      usernameError = null;
    }
    notifyListeners();
  }

  void validatePassword() {
    final password = passwordController.text.trim();
    if (password.isEmpty) {
      passwordError = 'Password is required';
    } else {
      passwordError = null;
    }
    notifyListeners();
  }
}
