%{
#include <stdio.h>

%}
%x BASIC CIAG_L CIAG_P
%%

<INITIAL>"#"[^\n]*(\n?)  {fprintf(yyout, "\n");}
<INITIAL>\"              {fprintf(yyout, yytext); BEGIN(CIAG_L);}
<INITIAL>["'".*"'"]      {fprintf(yyout, yytext);; BEGIN(CIAG_P);}

<BASIC>"#"    {BEGIN(INITIAL);}
<BASIC>"\n"   {}

<CIAG_L>\"         {fprintf(yyout, yytext); BEGIN(INITIAL);}
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
