package com.gurubank.utils;

import java.util.*;

/**
 * Utility class to store and manage multiple Customer IDs across test scenarios
 * Supports storing and retrieving customer IDs from Scenario execution context
 */
public class CustomerIdStorage {

    // Static storage for customer IDs across scenarios
    private static List<String> customerIds = Collections.synchronizedList(new ArrayList<>());
    private static Map<String, String> customerIdMap = Collections.synchronizedMap(new LinkedHashMap<>());

    /**
     * Add a single customer ID to storage
     * @param customerId The customer ID to store
     */
    public static void addCustomerId(String customerId) {
        if (customerId != null && !customerId.trim().isEmpty()) {
            customerIds.add(customerId);
            System.out.println("✓ Stored Customer ID: " + customerId + " (Total: " + customerIds.size() + ")");
        }
    }

    /**
     * Add a customer ID with a key for mapping
     * @param key Unique key for this customer
     * @param customerId The customer ID to store
     */
    public static void addCustomerId(String key, String customerId) {
        if (customerId != null && !customerId.trim().isEmpty()) {
            customerIdMap.put(key, customerId);
            customerIds.add(customerId);
            System.out.println("✓ Stored Customer ID '" + key + "': " + customerId + " (Total: " + customerIds.size() + ")");
        }
    }

    /**
     * Get a customer ID by index
     * @param index Zero-based index
     * @return Customer ID at the given index, or null if not found
     */
    public static String getCustomerId(int index) {
        if (index >= 0 && index < customerIds.size()) {
            return customerIds.get(index);
        }
        return null;
    }

    /**
     * Get a customer ID by key
     * @param key The key used when storing
     * @return Customer ID for the given key, or null if not found
     */
    public static String getCustomerId(String key) {
        return customerIdMap.get(key);
    }

    /**
     * Get all stored customer IDs
     * @return List of all customer IDs
     */
    public static List<String> getAllCustomerIds() {
        return new ArrayList<>(customerIds);
    }

    /**
     * Get customer ID by scenario/iteration number (1-based)
     * Example: getCustomerIdByIteration(1) returns first customer ID
     * @param iterationNumber 1-based iteration number
     * @return Customer ID for the given iteration
     */
    public static String getCustomerIdByIteration(int iterationNumber) {
        return getCustomerId(iterationNumber - 1);
    }

    /**
     * Get total number of stored customer IDs
     * @return Total count of stored IDs
     */
    public static int getTotalCustomerIds() {
        return customerIds.size();
    }

    /**
     * Check if a customer ID exists in storage
     * @param customerId The customer ID to check
     * @return true if exists, false otherwise
     */
    public static boolean exists(String customerId) {
        return customerIds.contains(customerId);
    }

    /**
     * Clear all stored customer IDs
     * Call this in @Before or @BeforeSuite to reset for new test run
     */
    public static void clearAll() {
        customerIds.clear();
        customerIdMap.clear();
        System.out.println("✓ Cleared all stored Customer IDs");
    }

    /**
     * Print all stored customer IDs for debugging
     */
    public static void printAll() {
        System.out.println("\n========== STORED CUSTOMER IDS ==========");
        if (customerIds.isEmpty()) {
            System.out.println("No customer IDs stored");
        } else {
            for (int i = 0; i < customerIds.size(); i++) {
                System.out.println((i + 1) + ". " + customerIds.get(i));
            }
        }
        System.out.println("=========================================\n");
    }
}

