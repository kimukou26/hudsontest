import groovy.lang.* 
import org.codehaus.groovy.control.*

SRCPATH="D:/workspace36/kohsuke/LTHudson/standalone"
LIBPATH="D:/workspace36/kohsuke/LTHudson/standalone/lib"
DLLPATH="D:/workspace36/kohsuke/LTHudson/standalone/dll"
String talk = "�������'/����'�����ĂˁB�������炭'�邼�A������'��"
println "talk=$talk"

enum VoiceType{
    f1("�����P�iAquesTalk���C�u�������Ɠ������́j"),
    m1("�j���P"),
    r1("���{�b�g���P"),
    f2("�����Q(�����P��藎�������������j"),
    m2("�j���Q(�j���P��荂�����j"),
    imd1("�����I�Ȑ��P"),
    dvd("�@�B�I�Ȑ�(��߂̃m�C�W�[�Ȋ����j"),
    jgr("�@�B�I�Ȑ�(���߂̍b���������j")
    
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