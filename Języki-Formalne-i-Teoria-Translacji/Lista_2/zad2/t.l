%{
#include <stdio.h>

%}
%x BASIC CIAG_P
%%

<INITIAL>"#"[^\n]+$      {fprintf(yyout, "");}
<INITIAl>["]{3}          {fprintf(yyout, "");}
<INITIAL>["'".*"'"]      {fprintf(yyout, yytext); BEGIN(CIAG_P);}


<BASIC>"#"    {BEGIN(INITIAL);}
<BASIC>"\n"   {}

<CIAG_P>["'".*"'"] {fprintf(yyout, yytext); BEGIN(INITIAL);}

%%
int yywrap(){}
int main() 
{
    extern FILE *yyin, *yyout;
    yyin = fopen("tests.py", "r");
    yyout = fopen("rewritten.py", "w");
    yylex(); 
    return 0;
}