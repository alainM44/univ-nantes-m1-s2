FILE=Rapport_Final_TER_RINCE_MARGUERITE
VIEWER=evince

.PHONY: makepdf makebiblio show clean

all: makepdf  makebiblio
	pdflatex $(FILE).tex

makepdf: $(FILE).tex 
	pdflatex $(FILE).tex

#makeglossary: $(FILE).glo
#	makeglossaries $(FILE).glo

makebiblio: $(FILE).aux
	bibtex $(FILE).aux

show:
	$(VIEWER) $(FILE).pdf &

clean:
	rm -f $(FILE).aux $(FILE).bbl $(FILE).blg $(FILE).glg $(FILE).glo $(FILE).gls $(FILE).ist $(FILE).lof $(FILE).log $(FILE).out $(FILE).toc

