#!/bin/sh

PTPLOT_HOME="$HOME/workspace/ptplot1"
CP="src:bin:bin/jsonic-1.2.0.jar:bin/epsgraphics-1.2.jar:bin/gnujpdf.jar"
if [ "`uname`" = "Darwin" ];then
  VM_OPTS="-Dapple.laf.useScreenMenuBar=true"
else
  VM_OPTS="-Dapple.laf.useScreenMenuBar=true"
fi
APP_OPTS="-width 760 -height 820 data-rp.plt"

cd $PTPLOT_HOME
exec java -Xmx2048m -classpath $CP $VM_OPTS Demo $APP_OPTS
