import groovy.lang.* 
import org.codehaus.groovy.control.*

HUDSON_HOME=args[0]
//String talk = "‚ä‚Á‚­‚è‚µ‚Ä‚¢‚Á‚Ä‚ËB‚µ‚½‚©‚ç‚­‚é‚¼A‚«‚ð‚Â‚¯‚ë"
String talk=args[1]
println "talk=$talk"

SRCPATH="$HUDSON_HOME/script"
LIBPATH="$HUDSON_HOME/script/libjna"
DLLPATH="$HUDSON_HOME/script/dll"


enum VoiceType{
    f1("—º‚PiAquesTalkƒ‰ƒCƒuƒ‰ƒŠ’†‚Æ“¯‚¶‚à‚Ìj"),
    m1("’jº‚P"),
    r1("ƒƒ{ƒbƒgº‚P"),
    f2("—º‚Q(—º‚P‚æ‚è—Ž‚¿’…‚¢‚½Š´‚¶j"),
    m2("’jº‚Q(’jº‚P‚æ‚è‚‚¢ºj"),
    imd1("’†«“I‚Èº‚P"),
    dvd("‹@ŠB“I‚Èº(’á‚ß‚ÌƒmƒCƒW[‚ÈŠ´‚¶j"),
    jgr("‹@ŠB“I‚Èº(‚‚ß‚Ìb‚‚¢Š´‚¶j")
    
    VoiceType(String note) {this.note= note}
    private final String note
    public String note() { return note }
    public static VoiceType random(){
        VoiceType[] values = this.values()
        return values[ (int)(Math.random()*100 % values.size())]
    }
}

//println VoiceType.values().size()
//println VoiceType.random()
//println Math.random()
//type = VoiceType.random()

type =VoiceType."${args[2]}"
DLLPATH="$DLLPATH/$type"
println "DLLPATH=$DLLPATH"
println "type=$type.note"


ClassLoader parent = ClassLoader.getSystemClassLoader()
CompilerConfiguration config = new CompilerConfiguration([
    sourceEncoding:'UTF-8',                //equals -Dgroovy.source.encoding=UTF-8
    targetDirectory:new File(SRCPATH)
])

GroovyClassLoader loader = new GroovyClassLoader(parent,config)
//jar 
new File("$LIBPATH").eachFileRecurse{ 
    if(it.name.endsWith('.jar')){
        loader.addURL it.toURL()
        //println it
    }
}
//loader.addClasspath "${HUDSON_HOME}/war/WEB-INF/lib/groovy-all-1.6.0.jar"
loader.addClasspath "${HUDSON_HOME}/war/WEB-INF/lib/groovy-all-1.7.5.jar"

fname="$SRCPATH/AquesTalkByJNA.groovy"
File fn = new File(fname)
def source = new GroovyCodeSource(fn,"UTF-8")        //equals -Dfile.encoding=UTF-8(since 1.7)
//def source = new GroovyCodeSource(fn)
def groovyClass = loader.parseClass(source)
def groovyObject = groovyClass.newInstance()

println "${groovyObject.class} start==>"
groovyObject.setProperty("args", [DLLPATH,talk]as Object[])
groovyObject.invokeMethod("run", [] as Object[]) 
println "${groovyObject.class} end <=="
