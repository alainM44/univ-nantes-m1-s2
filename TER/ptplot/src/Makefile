.SUFFIXES: 
.SUFFIXES: .class .java
.PHONY : doc clean

JAVAC    := javac

BIN_DIR  := ../bin
CP		 := .:$(BIN_DIR):$(BIN_DIR)/jsonic-1.2.0.jar:$(BIN_DIR)/epsgraphics-1.2.jar:$(BIN_DIR)/gnujpdf.jar

OBJECTS	 := ptolemy/plot/Plot.class \
	JsonPlot.class RPDemo.class RPDemoT.class ProjDemo.class RPPlot.class Demo.class \
	BoxFilter.class ColladaConverter.class \
	RPRSimulator.class

all: $(OBJECTS)

.java.class: 
	$(JAVAC) -classpath $(CP) $<
