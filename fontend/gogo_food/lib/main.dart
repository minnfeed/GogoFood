import 'package:flutter/material.dart';
import 'package:gogo_food/viewmodels/gogo_food_view_model.dart';
import 'package:gogo_food/views/gogo_food_screen.dart';
import 'package:provider/provider.dart';

void main() {
  runApp(
    ChangeNotifierProvider(
      create: (_) => GogoFoodViewModel()..startAnimation(),
      child: MaterialApp(
        debugShowCheckedModeBanner: false,
        initialRoute: '/',
        routes: {'/': (context) => GogoFoodScreen()},
      ),
    ),
  );
}
