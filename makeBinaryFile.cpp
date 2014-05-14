#include <cstdio>
typedef struct {
	double a; // ax**2 + 
	double b; // bx + 
	double c; // c
	int power; // =2 всегда
} squarePolynomial;
int main(int argc, char* argv[]){
    FILE *pFile;
    squarePolynomial input;
    char check;
    printf("insert a: ");   
    scanf("%lf",&input.a);
    printf("insert b: ");    
    scanf("%lf",&input.b);
    printf("insert c: ");    
    scanf("%lf",&input.c);
    input.power = 2;
    pFile = fopen ("input.bin", "wb");
    fwrite (&input , sizeof(double), 4, pFile);
    printf("One more?(y/n) ");    
    scanf("%c",&check);
    scanf("%c",&check);
    while (check == 121){
        printf("insert a: ");   
        scanf("%lf",&input.a);
        printf("insert b: ");    
        scanf("%lf",&input.b);
        printf("insert c: ");    
        scanf("%lf",&input.c);
        input.power = 2;
        fwrite (&input , sizeof(double), 4, pFile);
        printf("One more?(y/n) ");    
        scanf("%c",&check);
        scanf("%c",&check);
    }
    fclose (pFile);
    return 0;
}