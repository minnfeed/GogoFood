import 'dart:convert';

import 'package:flutter/material.dart';
import 'package:gogo_food/services/search_service.dart';
import 'package:http/http.dart' as http;

class SearchViewModel extends ChangeNotifier {
  final _searchService = SearchService();

  List<dynamic> _results = [];
  List<dynamic> get results => _results;

  bool _isLoading = false;
  bool get isLoading => _isLoading;

  Future<void> search(String query) async {
    _isLoading = true;
    notifyListeners();

    try {
      final respone = await http.get(
        Uri.parse('https://your-api-url.com/search?q=$query'),
      );

      if (respone.statusCode == 200) {
        _results = json.decode(respone.body)['data'];
      } else {
        _results = [];
      }
    } catch (e) {
      _results = [];
    }

    _isLoading = false;
    notifyListeners();
  }
}
