import groovy.lang.* 
import org.codehaus.groovy.control.*

SRCPATH="D:/workspace36/kohsuke/LTHudson/standalone"
LIBPATH="D:/workspace36/kohsuke/LTHudson/standalone/lib"
DLLPATH="D:/workspace36/kohsuke/LTHudson/standalone/dll"
String talk = "ゆっくり'/して'いってね。したからく'るぞ、きをつけ'ろ"
println "talk=$talk"

enum VoiceType{
    f1("女声１（AquesTalkライブラリ中と同じもの）"),
    m1("男声１"),
    r1("ロボット声１"),
    f2("女声２(女声１より落ち着いた感じ）"),
    m2("男声２(男声１より高い声）"),
    imd1("中性的な声１"),
    dvd("機械的な声(低めのノイジーな感じ）"),
    jgr("機械的な声(高めの甲高い感じ）")
    
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
type = VoiceType.random()
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
//loader.addClasspath "C:/opt/groovy-1.7.5/embeddable/groovy-all-1.7.5.jar"

fname="$SRCPATH/AquesTalkByJNA.groovy"
File fn = new File(fname)
def source = new GroovyCodeSource(fn,"UTF-8")        //equals -Dfile.encoding=UTF-8
def groovyClass = loader.parseClass(source)
def groovyObject = groovyClass.newInstance()

println "${groovyObject.class} start==>"
groovyObject.setProperty("args", [DLLPATH,talk]as Object[])
groovyObject.invokeMethod("run", [] as Object[]) 
println "${groovyObject.class} end <=="