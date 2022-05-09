/*
A warehouse manager needs to create a shipment to fill a truck. All of the products in the warehouse are in boxes of
the same size. Each product is packed in some number of units per box. Given the number of boxes the truck can hold,
determine the max number of units of any mix of products that can be shipped.

 */
public class EfficientShippingHackerrank {
    /*
    bounded knapsack problem
     */
    public static int[][] memo = new int[3][4]; // includes 1 buffer col since actual trucksize doesn't start at 0 like idx
    public static int getMaxUnitsRecursive(int[] boxes, int[] unitsPerBox, int truckSize) {
        return getMaxUnitsRecursive(boxes, unitsPerBox, truckSize, 0, 0, 0);
    }

    public static int getMaxUnitsRecursive(int[] boxes, int[] unitsPerBox, int truckSizeRemaining, int idx, int usedForThisBox, int maxCummulativeUnits) {
        // base case I
        if (truckSizeRemaining == 0) {
            return maxCummulativeUnits;
        }

        // base case II
        if (idx == unitsPerBox.length-1 && boxes[idx] == usedForThisBox) {
            // leaf i.e end of list and we've exhausted all boxes allowable on this index
            return maxCummulativeUnits;
        }

        // base case III
        if (idx == unitsPerBox.length) {
            // leaf i.e end of list and we've exhausted all boxes allowable on this index
            return maxCummulativeUnits;
        }

        // if ran out of number of this same box
        if (boxes[idx] == usedForThisBox) {
            // move on to next idx'd box
            return getMaxUnitsRecursive(boxes, unitsPerBox, truckSizeRemaining, idx+1, 0, maxCummulativeUnits);
        }

        // stick to same idx or move up to next idx
        return Math.max(getMaxUnitsRecursive(boxes, unitsPerBox, truckSizeRemaining-1, idx, usedForThisBox+1, maxCummulativeUnits + unitsPerBox[idx]),
                getMaxUnitsRecursive(boxes, unitsPerBox, truckSizeRemaining, idx+1, 0, maxCummulativeUnits));
    }


    public static int getMaxUnitsMemoization(int[] boxes, int[] unitsPerBox, int truckSize) {
        return getMaxUnitsMemoization(boxes, unitsPerBox, truckSize, 0, 0, 0);
    }

    public static int getMaxUnitsMemoization(int[] boxes, int[] unitsPerBox, int truckSizeRemaining, int idx, int usedForThisBox, int maxCummulativeUnits) {
        // base case I
        if (truckSizeRemaining == 0) {
            return maxCummulativeUnits;
        }

        // base case II
        if (idx == unitsPerBox.length-1 && boxes[idx] == usedForThisBox) {
            // leaf i.e end of list and we've exhausted all boxes allowable on this index
            return maxCummulativeUnits;
        }

        // base case III
        if (idx == unitsPerBox.length) {
            // leaf i.e end of list and we've exhausted all boxes allowable on this index
            return maxCummulativeUnits;
        }

        // if ran out of number of this same box
        if (boxes[idx] == usedForThisBox) {
            // move on to next idx'd box
            // first check to return from dp table
            if (memo[idx][truckSizeRemaining] != 0) {
                return memo[idx][truckSizeRemaining];
            }
            int res = getMaxUnitsMemoization(boxes, unitsPerBox, truckSizeRemaining, idx+1, 0, maxCummulativeUnits);
            memo[idx][truckSizeRemaining] = res;
            return memo[idx][truckSizeRemaining];
        }

        // stick to same idx or move up to next idx
        // first check dp table
        if (memo[idx][truckSizeRemaining] != 0) {
            return memo[idx][truckSizeRemaining];
        }
        int res = Math.max(getMaxUnitsMemoization(boxes, unitsPerBox, truckSizeRemaining-1, idx, usedForThisBox+1, maxCummulativeUnits + unitsPerBox[idx]),
                getMaxUnitsMemoization(boxes, unitsPerBox, truckSizeRemaining, idx+1, 0, maxCummulativeUnits));
        memo[idx][truckSizeRemaining] = res;
        return memo[idx][truckSizeRemaining];
    }

    public static void main(String[] args) {
        int[] boxes = {1, 2, 3};
        int[] unitsPerBox = {3, 2, 1};
        System.out.println("__Recursive__ " );
        int maxProfit = getMaxUnitsRecursive(boxes, unitsPerBox, 3);
        System.out.println("Maximum number of units that can be shipped is ---> " + maxProfit);

        System.out.println(" " );
        System.out.println("__Memoization__ " );
        int maxProfitMemo = getMaxUnitsMemoization(boxes, unitsPerBox, 3);
        System.out.println("Maximum number of units that can be shipped is ---> " + maxProfitMemo);

        System.out.println(" " );
        for (int[] rows: memo) {
            for (int cols: rows) {
                System.out.println(cols);
            }
        }
    }
}


