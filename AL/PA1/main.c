#include <stdio.h>
#include <string.h>
#include <stdlib.h>

#define MAX_ITEM 10000
#define MAX_SIZE 2000

int list_size = 0;
int attr_size = 1;
int key = -1;
int buf_len;
int list_order[MAX_ITEM];
char *list[MAX_ITEM];
char *attributes[MAX_ITEM];
char buf[MAX_SIZE];
int sorted_list[MAX_ITEM];

void loadAndprint(FILE *fp1, FILE *fp2);
void Merge(char **attributes, int *order, int *sorted, int left, int mid, int right);
void Merge_Sort(char **attributes, int *order, int *sorted_list, int left, int right);

void main()
{
    char *path1 = "hw1_input.txt";
    char *path2 = "hw1_output.txt";
    FILE *fp1, *fp2;

    if ((fp1 = fopen(path1, "r")) == NULL)
    {
        printf("Failed to open file");
        exit(1);
    }
    if ((fp2 = fopen(path2, "w")) == NULL)
    {
        printf("Failed to open file");
        exit(1);
    }
    loadAndprint(fp1, fp2);

    fclose(fp1);
    fclose(fp2);
}

void loadAndprint(FILE *fp1, FILE *fp2)
{
    fgets(buf, MAX_ITEM, fp1); // get # of list
    list_size = atoi(buf);
    if (list_size <= 0)
        return;
    fgets(buf, sizeof(buf), fp1); // get $
    fgets(buf, sizeof(buf), fp1); // get attributes
    buf_len = strlen(buf);
    for (int i = 0; i < buf_len; i++) // get the order of key attributes
    {
        char cur = buf[i];
        if (cur == ':')
            attr_size++;
        else if (key == -1 && cur == '*')
            key = attr_size - 1;
    }
    fgets(buf, sizeof(buf), fp1); // get $
    for (int i = 0; i < list_size; i++)
    {
        char *attr_ptr;
        fgets(buf, sizeof(buf), fp1); // get 1 line of list
        buf_len = strlen(buf);
        if (buf[buf_len - 1] == '\n')
        {
            buf[buf_len - 1] = '\0';
        }
        list[i] = strdup(buf); // copy 1 line to list
        attr_ptr = strtok(buf, ":");
        for (int i = 1; i <= key; i++)
        {
            attr_ptr = strtok(NULL, ":");
        }
        attributes[i] = strdup(attr_ptr); // copy key attribute to attributes
    }

    for (int i = 0; i < list_size; i++) // add index value to the list_order for reducing of copying
    {
        list_order[i] = i;
    }

    Merge_Sort(attributes, list_order, sorted_list, 0, list_size - 1);

    for (int i = 0; i < list_size; i++) // print the sorted list to hw1_output.txt
    {
        fprintf(fp2, "%s\n", list[list_order[i]]);
    }
    for (int i = 0; i < list_size; i++) // freeing for strdup()
    {
        free(list[i]);
        free(attributes[i]);
    }
}

void Merge(char **attributes, int *order, int *sorted, int left, int mid, int right)
{
    int i = left;
    int low = left;
    int high = mid + 1;

    while (low <= mid && high <= right)
    {
        if ((strcmp(attributes[order[low]], attributes[order[high]])) < 1)
            sorted[i++] = order[low++];
        else
            sorted[i++] = order[high++];
    }
    while (low <= mid)
    {
        sorted[i++] = order[low++];
    }
    while (high <= right)
    {
        sorted[i++] = order[high++];
    }
    for (int i = left; i <= right; i++)
    {
        order[i] = sorted[i];
    }
}

void Merge_Sort(char **attributes, int *order, int *sorted_list, int left, int right)
{
    if (left >= right)
        return;
    int mid = (left + right) / 2;
    Merge_Sort(attributes, order, sorted_list, left, mid);
    Merge_Sort(attributes, order, sorted_list, mid + 1, right);

    Merge(attributes, order, sorted_list, left, mid, right);
}