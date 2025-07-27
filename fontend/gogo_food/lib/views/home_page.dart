import 'package:flutter/material.dart';
import 'package:gogo_food/services/mock_data.dart';
import 'package:gogo_food/viewmodels/home_view_model.dart';
import 'package:gogo_food/viewmodels/search_view_model.dart';
import 'package:gogo_food/views/home/widgets/category_grid_widget.dart';
import 'package:gogo_food/views/home/widgets/home_app_bar.dart';
import 'package:gogo_food/views/home/widgets/search_bar_widget.dart';
import 'package:gogo_food/views/home/widgets/special_offer_widget.dart';
import 'package:gogo_food/views/search_result_screen.dart';
import 'package:provider/provider.dart';

class HomePage extends StatefulWidget {
  @override
  State<StatefulWidget> createState() {
    return _HomePage();
  }
}

class _HomePage extends State<HomePage> {
  final TextEditingController _searchController = TextEditingController();

  void _handleSearch(BuildContext context) {
    final query = _searchController.text.trim();
    if (query.isEmpty) return;

    final searchVM = Provider.of<SearchViewModel>(context, listen: false);
    searchVM.search(query).then((_) {
      Navigator.push(
        context,
        MaterialPageRoute(builder: (_) => SearchResultScreen(query: query)),
      );
    });
  }

  @override
  Widget build(BuildContext context) {
    final vm = context.watch<HomeViewModel>();
    return Scaffold(
      backgroundColor: Colors.grey.shade100,
      body: SafeArea(
        child: Column(
          children: [
            HomeAppBar(
              location: vm.location,
              searchBar: SearchBarWidget(
                controller: _searchController,
                onSearch: () => _handleSearch(context),
              ),
            ),
            Expanded(
              child: SingleChildScrollView(
                child: Column(
                  children: [
                    SpecialOfferWidget(
                      imageUrls: [
                        'assets/images/banner1.png',
                        'assets/images/banner2.png',
                        'assets/images/banner3.png',
                      ],
                      onTap: (index) {
                        CategoryGridWidget(categories: mockCategories);
                      },
                    ),
                  ],
                ),
              ),
            ),
          ],
        ),
      ),
    );
  }
}
