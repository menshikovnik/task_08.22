package vsu.cs.menshikovnik.logic;

public class Solution {
    public static int[][] calc(int[][] myArray) {
        int r = myArray.length;
        int c = myArray[0].length;
        int[][] newArray = new int[r][c];
        int[][] array = new int[r][c];
        for (int y = 0; y < myArray.length; y++) {
            for (int x = 0; x < myArray[0].length; x++) {
                if (array[y][x] == 0) {
                    int count = getCountFriendly(myArray, array, r, c, y, x, myArray[y][x]);
                    arrayFilling(myArray, array, newArray, r, c, y, x, myArray[y][x], count - 1);
                }
            }
        }
        return newArray;
    }

    public static int getCountFriendly(int[][] myArray, int[][] array, int r, int c, int y, int x, int value) {
        if (y < 0 || x < 0 || x == c || y == r || array[y][x] > 0 || myArray[y][x] != value) return 0;
        array[y][x] = 1;
        int result = 1;
        result += getCountFriendly(myArray, array, r, c, y - 1, x, value);
        result += getCountFriendly(myArray, array, r, c, y, x - 1, value);
        result += getCountFriendly(myArray, array, r, c, y + 1, x, value);  // проход flood fill для подсчета
        result += getCountFriendly(myArray, array, r, c, y, x + 1, value);
        return result;
    }

    public static void arrayFilling(int[][] myArray, int[][] array, int[][] newArray, int r, int c, int y, int x,
                                   int value, int fillArray) {
        if (y < 0 || x < 0 || x == c || y == r || array[y][x] == 2 || myArray[y][x] != value) return;
        array[y][x] = 2;
        newArray[y][x] = fillArray;
        arrayFilling(myArray, array, newArray, r, c, y - 1, x, value, fillArray);
        arrayFilling(myArray, array, newArray, r, c, y, x - 1, value, fillArray);
        arrayFilling(myArray, array, newArray, r, c, y + 1, x, value, fillArray); // flood fill для заполнения нового массива
        arrayFilling(myArray, array, newArray, r, c, y, x + 1, value, fillArray);
    }
}
