%{
#include <stdio.h> 
int line = 0, words = 0;
 
%%
(?# remove any unnecessary empty lines)
^[[:blank:]]*\n	fprintf(yyout, "");
(?# remove whitespaces at the beginning)
^[[:blank:]]+	fprintf(yyout, "");
(?# remove spaces inside and replaces with single space)
[ \t]+          fprintf(yyout, " ");
(?# remove spaces at the end of line)
[[:blank:]]+$   fprintf(yyout, "");
%%
 
int yywrap(){}
 
int main(int argc, char **argv) {
    extern FILE *yyin, *yyout;

    yyin = fopen("a1.txt", "r");
    yyout = fopen("rewritten.txt", "w");
 
    yylex();
    return 0;
    }