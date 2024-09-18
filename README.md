# HashTable Implementation in Java

## Overview

This project provides a basic implementation of a hash table in Java using the `HashTable` interface and the `HashTableImpl` class. The hash table uses **open addressing with linear probing** to resolve collisions. The table automatically resizes when it becomes full and shrinks when the load factor drops below a certain ratio. This implementation includes methods to insert, search, remove keys, and manage the table size.

## Classes and Methods

### `HashTable` Interface

Defines the structure of the hash table with the following methods:

- **`insert(int key, Object value)`**: Inserts a key-value pair into the hash table. If the key already exists, the value is updated.
- **`search(int key)`**: Searches for a key and returns its associated value. Returns `null` if the key is not found.
- **`remove(int key)`**: Removes the key-value pair associated with the given key.
- **`size()`**: Returns the current number of elements in the table.
- **`keys()`**: Returns an array of all keys in the table.
- **`getInstance()`**: Returns an instance of `HashTableImpl`.

### `HashTableImpl` Class

Implements the `HashTable` interface with the following features:

- **Internal Structure**: Uses an array of `Entry` objects to store key-value pairs. Handles collisions using **linear probing**.
- **Resizing**: Automatically resizes when it reaches the maximum capacity and shrinks when the load factor drops below `SHRINK_RATIO`.
- **Helper Methods**:
  - **`hashFunction(int key)`**: Computes the hash index for the key.
  - **`isUniqueKey(int key)`**: Checks if a key is unique in the table.
  - **`findSlot(int key)`**: Finds the appropriate slot for insertion.
  - **`replaceValue(int key, Object value)`**: Replaces the value of an existing key.
  - **`resize(int newCapacity)`**: Resizes the hash table to the new capacity.

### `Main` Class

The `Main` class tests the hash table with the following operations:

- Inserting key-value pairs.
- Searching for keys.
- Updating existing keys.
- Removing keys.
- Printing current keys in the table.
- Checking the table size at various stages.
