class Solution {
    public int myAtoi(String str) {
        List<Character> strList = new ArrayList<Character>();
        boolean digitMode = false;
        boolean isNegative = false;
        int count = -1;

        char strArray[] = str.toCharArray();
        for (int i = 0; i < strArray.length; i++) {
            char digit = strArray[i];
            if (!digitMode && digit == ' ') {
                continue;
            }

            if (digit == '-' && !digitMode) {
                isNegative = true;
                digitMode = true;
            } else if (digit == '+' && !digitMode) {
              digitMode = true;
            } else if (digit >= '0' && digit <= '9') {
                digitMode = true;
                if (digit == '0' && strList.size() == 0) {
                    continue;
                }
                strList.add(digit);
                count++;
            } else if (digitMode) {
                break;
            }

            if (!digitMode) {
                return 0;
            }
        }
        
        //bail if the number is "super" large
        if (strList.size() > 10) {
            return isNegative ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        }

        long num = 0;
        for (Character c : strList) {
            if (count > 0) {
                int n = (int)c - 48;

                long base = 1;
                for (int i = 1; i <= count; i++) {
                    base *= 10;
                }

                num += (n * base);
            } else {
                num += (int)c - 48;
            }
            count--;
        }

        num = isNegative ? (-1 * num) : num;
        if (num > Integer.MAX_VALUE)
            num = Integer.MAX_VALUE;
        else if (num < Integer.MIN_VALUE) {
            num = Integer.MIN_VALUE;
        }
        
        return (int)num;
    }
}
