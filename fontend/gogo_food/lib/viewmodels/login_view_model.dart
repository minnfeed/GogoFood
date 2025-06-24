import 'package:flutter/material.dart';
import 'package:gogo_food/views/signup_screen.dart';

class LoginViewModel extends ChangeNotifier {
  void signInWithFacebook() {}

  void signInWithGoogle() {}
  void startWithEmailOrPhone() {}
  void signup(BuildContext context) {
    Navigator.push(
      context,
      MaterialPageRoute(builder: (context) => const SignupScreen()),
    );
  }
}
