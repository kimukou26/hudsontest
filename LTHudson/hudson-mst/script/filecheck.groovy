FILEPATH=args[0]
HUDSON_HOME=args[1]
JOB_NAME=args[2]
JOB_NAME_NEXT=args[3]
HUDSON_URL=args[args.size()-1]


def fnum = new File("$HUDSON_HOME/jobs/$JOB_NAME/nextJobNumber")
def num = fnum.exists() ? new Integer(fnum.text) : Integer.valueOf("0")


//Groovy classLoader add jar
ClassLoader parent = ClassLoader.getSystemClassLoader()
GroovyClassLoader loader = new GroovyClassLoader(parent)

//http unit jar location $HUDSON_HOME/script/lib
new File("$HUDSON_HOME/script/lib").eachFileRecurse{ 
    loader.addURL it.toURL()
    //println it
}
//hudson.war jar
new File("$HUDSON_HOME/war/WEB-INF/lib").eachFileRecurse{ 
    loader.addURL it.toURL()
    //println it
}

fname="$HUDSON_HOME/script/jobpost.groovy"
def groovyClass = loader.parseClass(new File(fname))
def groovyObject = groovyClass.newInstance()


println "($num)[$JOB_NAME]FILEPATH=${FILEPATH}"
def fn =new File(FILEPATH)
if(fn.exists()){
  println "file found!"
  fnum.write("0")
  String url="$HUDSON_URL/job/$JOB_NAME_NEXT/build?delay=30sec"
  groovyObject.setProperty("args", [ url ]as Object[])
  groovyObject.invokeMethod("run", [] as Object[])  
}
else if(num>2){
  println "[$JOB_NAME] job skip"
  fnum.write("0")
  String url="$HUDSON_URL/job/$JOB_NAME_NEXT/build?delay=30sec"
  groovyObject.setProperty("args", [ url ]as Object[])
  groovyObject.invokeMethod("run", [] as Object[])  
}
else{
  println "recovery.done not found"
  num++
  fnum.write("${num}")
  String url="$HUDSON_URL/job/$JOB_NAME/build?delay=30sec"
  println "url=$url"
  groovyObject.setProperty("args", [ url ]as Object[])
  groovyObject.invokeMethod("run", [] as Object[])
  throw new Exception("file not found")
}
