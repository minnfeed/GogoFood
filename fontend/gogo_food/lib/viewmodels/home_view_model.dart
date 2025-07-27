import 'package:flutter/material.dart';
import 'package:geocoding/geocoding.dart';
import 'package:geolocator/geolocator.dart';

class HomeViewModel extends ChangeNotifier {
  String _location = "Loading...";
  String get location => _location;

  // TextEditingController searchController;

  HomeViewModel() {
    _initLocation();
  }

  Future<void> _initLocation() async {
    final hasPermission = await _handleLocationPermission();
    if (!hasPermission) return;

    final position = await Geolocator.getCurrentPosition(
      desiredAccuracy: LocationAccuracy.high,
    );

    await _updateAddress(position);

    Geolocator.getPositionStream(
      locationSettings: const LocationSettings(
        accuracy: LocationAccuracy.high,
        distanceFilter: 50,
      ),
    ).listen((Position newPosition) {
      _updateAddress(newPosition);
    });
  }

  Future<void> _updateAddress(Position position) async {
    try {
      final placemark = await placemarkFromCoordinates(
        position.latitude,
        position.longitude,
      );
      final place = placemark.first;
      _location = '${place.street ?? ''}, ${place.locality ?? ''}';
    } catch (e) {
      _location = 'Unable to get location';
    }
    notifyListeners();
  }

  Future<bool> _handleLocationPermission() async {
    bool serviceEnabled = await Geolocator.isLocationServiceEnabled();
    if (!serviceEnabled) {
      _location = 'Location services are disabled.';
      notifyListeners();
      return false;
    }

    LocationPermission permission = await Geolocator.checkPermission();
    if (permission == LocationPermission.denied) {
      permission = await Geolocator.requestPermission();
      if (permission == LocationPermission.denied) {
        _location = 'Location permission denied';
        notifyListeners();
        return false;
      }
    }

    if (permission == LocationPermission.deniedForever) {
      _location = 'Location permissions permanently denied';
      notifyListeners();
      return false;
    }

    return true;
  }
}
