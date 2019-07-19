package javagym;

public class MergeSort
{
    public void merge(double arr[], int[] index, int l, int m, int r)
    {
        int n1 = m - l + 1;
        int n2 = r - m;

        /* Create temp arrays */
        double[] L = new double[n1];
        double[] R = new double[n2];
        int[] indexL = new int[n1];
        int[] indexR = new int[n2];

        for (int i=0; i<n1; ++i) {
            L[i] = arr[l + i];
            indexL[i] = index[l + i];
        }
        for (int j=0; j<n2; ++j) {
            R[j] = arr[m + 1 + j];
            indexR[j] = index[m + 1 + j];
        }

        int i = 0, j = 0;

        int k = l;
        while (i < n1 && j < n2)
        {
            if (L[i] <= R[j])
            {
                arr[k] = L[i];
                index[k] = indexL[i];
                i++;
            }
            else
            {
                arr[k] = R[j];
                index[k] = indexR[j];
                j++;
            }
            k++;
        }

        while (i < n1)
        {
            arr[k] = L[i];
            index[k] = indexL[i];
            i++;
            k++;
        }

        while (j < n2)
        {
            arr[k] = R[j];
            index[k] = indexR[j];
            j++;
            k++;
        }
    }

    public void sort(double arr[], int index[], int l, int r)
    {
        if (l < r)
        {
            int m = (l+r)/2;

            sort(arr, index, l, m);
            sort(arr, index, m+1, r);

            merge(arr, index, l, m, r);
        }
    }
}
