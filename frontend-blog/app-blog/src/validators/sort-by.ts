type Comparable = string | number;

/**
 * Sorts an array based on a property of each item.
 * @param property The property to base the sort onto
 */
export function sortByProperty<T = any, P extends keyof T = keyof T>(property: P): (a: T, b: T) => 0 | 1 | -1 {
    return (a: T, b: T) => {
        const aProp = a[property];
        const bProp = b[property];

        if (aProp > bProp) {
            return 1;
        }
        if (aProp < bProp) {
            return -1;
        }
        return 0;
    }
}
