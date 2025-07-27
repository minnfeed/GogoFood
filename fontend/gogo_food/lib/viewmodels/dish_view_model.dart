import 'package:flutter/material.dart';
import 'package:gogo_food/models/dish.dart';
import 'package:gogo_food/services/mock_data.dart';

class DishViewModel extends ChangeNotifier {
  List<Dish> _currentDishes = [];
  List<Dish> get currentDishes => _currentDishes;

  void loadDishesByCategory(String category) {
    final selected = mockDishes.firstWhere(
      (c) => c.categoryTitle == category,
      orElse: () => DishCategory(categoryTitle: category, dishes: []),
    );
    _currentDishes = selected.dishes;
    notifyListeners();
  }

  void loadAllDishes() {
    _currentDishes = mockDishes.expand((e) => e.dishes).toList();
    notifyListeners();
  }
}
