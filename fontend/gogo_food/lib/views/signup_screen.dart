import 'package:flutter/material.dart';
import 'package:gogo_food/viewmodels/signup_view_model.dart';
import 'package:google_fonts/google_fonts.dart';

class SignupScreen extends StatefulWidget {
  const SignupScreen({super.key});
  @override
  State<SignupScreen> createState() {
    return _SignupScreen();
  }
}

class _SignupScreen extends State<SignupScreen> {
  @override
  Widget build(BuildContext context) {
    final size = MediaQuery.of(context).size;
    final vm = SignupViewModel();

    return Scaffold(
      appBar: AppBar(
        centerTitle: true,
        title: Text(
          'Sign Up',
          style: GoogleFonts.inter(
            fontSize: 22,
            fontWeight: FontWeight.bold,
            color: Colors.black,
          ),
          textAlign: TextAlign.center,
        ),
        elevation: 0,
        leading: BackButton(color: Colors.black),
      ),
      backgroundColor: Colors.grey.shade100,
      body: SingleChildScrollView(
        padding: EdgeInsetsGeometry.symmetric(horizontal: 16),
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.center,
          children: [
            _buildTextField(
              label: 'Full name',
              hintText: 'Enter full name',
              controller: vm.fullNameController,
              errorText: vm.fullNameError,
            ),
            const SizedBox(height: 12),
            _buildTextField(
              label: 'Phone or Email',
              hintText: 'Enter phone or email',
              controller: vm.emailPhoneControler,
              errorText: vm.emailPhoneError,
            ),
            const SizedBox(height: 12),
            _buildTextField(
              label: 'Password',
              hintText: 'Enter password',
              controller: vm.passwordController,
              obscureText: !vm.showPassword,
              toggleVisibility: vm.togglePasswordVisibility,
              isPassword: true,
              errorText: vm.passwordError,
            ),
            const SizedBox(height: 12),
            _buildTextField(
              label: 'Confirm Password',
              hintText: 'Enter password',
              controller: vm.confirmPasswordController,
              obscureText: !vm.showConfirmPassword,
              toggleVisibility: vm.toggleConfirmPasswordVisibility,
              isPassword: true,
              errorText: vm.confirmPasswordError,
            ),
            const SizedBox(height: 12),
            RichText(
              text: TextSpan(
                style: GoogleFonts.sofiaSans(fontSize: 18, color: Colors.black),
                children: const [
                  TextSpan(
                    text:
                        'By clicking Create account, you agree to the system\'s ',
                  ),
                  TextSpan(
                    text: 'Terms and policies',
                    style: TextStyle(color: Colors.redAccent),
                  ),
                ],
              ),
            ),
            const SizedBox(height: 32),
            Center(
              child: ElevatedButton(
                onPressed: vm.signup,
                style: ElevatedButton.styleFrom(
                  backgroundColor: Colors.grey,
                  shape: RoundedRectangleBorder(
                    borderRadius: BorderRadius.circular(24),
                  ),
                  padding: const EdgeInsets.symmetric(
                    horizontal: 32,
                    vertical: 14,
                  ),
                ),
                child: const Text(
                  'SIGN UP',
                  style: TextStyle(color: Colors.black),
                ),
              ),
            ),
            const SizedBox(height: 16),
            Row(
              mainAxisAlignment: MainAxisAlignment.center,
              children: [
                const Text(
                  'Already have an account?',
                  style: TextStyle(fontSize: 16),
                ),
                TextButton(
                  onPressed: vm.login,
                  child: const Text(
                    'Login',
                    style: TextStyle(color: Colors.redAccent, fontSize: 18),
                  ),
                ),
              ],
            ),
            const SizedBox(height: 12),
            Row(
              children: const [
                Expanded(child: Divider(endIndent: 10)),
                Text('Sign up with'),
                Expanded(child: Divider(indent: 10)),
              ],
            ),
            const SizedBox(height: 12),
            Row(
              mainAxisAlignment: MainAxisAlignment.spaceAround,
              children: [
                _socialButton(
                  'Facebook',
                  'assets/images/facebook.png',
                  vm.signInWithFacebook,
                ),
                _socialButton(
                  'Google',
                  'assets/images/google.png',
                  vm.signInWithGoogle,
                ),
              ],
            ),
          ],
        ),
      ),
    );
  }

  Widget _buildTextField({
    required String label,
    required String hintText,
    required TextEditingController controller,
    String? errorText,
    bool obscureText = false,
    bool isPassword = false,
    VoidCallback? toggleVisibility,
  }) {
    return Column(
      crossAxisAlignment: CrossAxisAlignment.start,
      children: [
        Text(
          label,
          style: const TextStyle(fontWeight: FontWeight.bold, fontSize: 18),
        ),
        const SizedBox(height: 6),
        TextField(
          controller: controller,
          obscureText: obscureText,
          decoration: InputDecoration(
            hintText: hintText,
            border: OutlineInputBorder(borderRadius: BorderRadius.circular(8)),
            errorText: errorText,
            suffixIcon: isPassword
                ? IconButton(
                    onPressed: toggleVisibility,
                    icon: Icon(
                      obscureText ? Icons.visibility_off : Icons.visibility,
                    ),
                  )
                : null,
          ),
        ),
      ],
    );
  }

  Widget _socialButton(String text, String iconPath, VoidCallback onPressed) {
    return ElevatedButton.icon(
      onPressed: onPressed,
      label: Text(text),
      icon: Image.asset(iconPath, width: 24, height: 24),
      style: ElevatedButton.styleFrom(
        backgroundColor: Colors.white,
        foregroundColor: Colors.black,
        padding: const EdgeInsets.symmetric(horizontal: 12, vertical: 8),
        shape: RoundedRectangleBorder(borderRadius: BorderRadius.circular(24)),
      ),
    );
  }
}
