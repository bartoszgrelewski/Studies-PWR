%{
#include <stdio.h>
int d = 0;
%}

%x instring
%x ininclude
%x singlecomment
%x multicomment
%x dsinglecomment
%x dmulticomment

%%
\"                          { fprintf(yyout,"%s", yytext); BEGIN(instring); }
<instring>\"                { fprintf(yyout,"%s", yytext); BEGIN(INITIAL); }
<instring>.                 { fprintf(yyout,"%s", yytext); }        

\<                          { fprintf(yyout,"%s", yytext); BEGIN(ininclude); }
<ininclude>>                { fprintf(yyout,"%s", yytext); BEGIN(INITIAL); }
<ininclude>.|\n             { fprintf(yyout,"%s", yytext); }

\/(\\\n)*\/(\\\n)*(\/|!)    { if (d == 1) fprintf(yyout,"%s", yytext); BEGIN(dsinglecomment); }
<dsinglecomment>.*\\\n      { if (d == 1) fprintf(yyout,"%s", yytext); }
<dsinglecomment>.           { if (d == 1) fprintf(yyout,"%s", yytext); }
<dsinglecomment>[^\\]\n     { if (d == 1) fprintf(yyout,"%s", yytext); else fprintf(yyout,("\n"), yytext); BEGIN(INITIAL); }

\/(\\\n)*\/                 { BEGIN(singlecomment); }
<singlecomment>.*\\\n       { }
<singlecomment>.            { }
<singlecomment>[^\\]\n      { fprintf(yyout,("\n"), yytext); BEGIN(INITIAL); }

\/(\\\n)*\*(\\\n)*(\*|!)    { if (d == 1) fprintf(yyout,"%s", yytext); BEGIN(dmulticomment); }
<dmulticomment>.|\n         { if (d == 1) fprintf(yyout,"%s", yytext); }
<dmulticomment>\*(\\\n)*\/  { if (d == 1) fprintf(yyout,"%s", yytext); else fprintf(yyout,("\n"), yytext); BEGIN(INITIAL); }

\/(\\\n)*\*                 { BEGIN(multicomment); }
<multicomment>.|\n          { }
<multicomment>\*(\\\n)*\/   {fprintf(yyout,("\n"), yytext); BEGIN(INITIAL); }

%%

int yywrap(){}
int main(int argc, char** argv) 
{
    if (argc == 2 && !strcmp(argv[1], "zostaw")){
        d = 1;
    }
    extern FILE *yyin, *yyout;
    yyin = fopen("a.cpp", "r");
    yyout = fopen("b.cpp", "w");
    yylex(); 
    return 0;
}