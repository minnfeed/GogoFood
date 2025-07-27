import 'package:flutter/material.dart';
import 'package:gogo_food/models/dish.dart';
import 'package:gogo_food/services/mock_data.dart';
import 'package:gogo_food/views/category/category_result_screen.dart';
import 'package:gogo_food/models/category.dart';
import 'package:google_fonts/google_fonts.dart';

class CategoryGridWidget extends StatelessWidget {
  final List<Category> categories;

  const CategoryGridWidget({super.key, required this.categories});

  @override
  Widget build(BuildContext context) {
    return Padding(
      padding: const EdgeInsets.symmetric(horizontal: 16),
      child: GridView.builder(
        gridDelegate: const SliverGridDelegateWithFixedCrossAxisCount(
          crossAxisCount: 4,
          mainAxisSpacing: 16,
          crossAxisSpacing: 16,
          childAspectRatio: 0.8,
        ),
        itemBuilder: (context, index) {
          final category = categories[index];
          return InkWell(
            borderRadius: BorderRadius.circular(8),
            onTap: () {
              if (category.title == 'Other') {
                Navigator.push(
                  context,
                  MaterialPageRoute(
                    builder: (_) => CategoryResultScreen(
                      categoryTitle: 'All Categories',
                      dishes: mockDishes.expand((e) => e.dishes).toList(),
                    ),
                  ),
                );
              } else {
                final selected = mockDishes.firstWhere(
                  (e) => e.categoryTitle == category.title,
                  orElse: () =>
                      DishCategory(categoryTitle: category.title, dishes: []),
                );
                Navigator.push(
                  context,
                  MaterialPageRoute(
                    builder: (_) => CategoryResultScreen(
                      categoryTitle: selected.categoryTitle,
                      dishes: selected.dishes,
                    ),
                  ),
                );
              }
            },
            child: Column(
              children: [
                Image.asset(category.imagePath, height: 40, width: 40),
                const SizedBox(height: 8),
                Text(
                  category.title,
                  style: GoogleFonts.inter(fontSize: 14),
                  textAlign: TextAlign.center,
                ),
              ],
            ),
          );
        },
      ),
    );
  }
}
