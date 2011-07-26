import com.sun.jna.Library
import com.sun.jna.Native
import com.sun.jna.NativeLibrary
import com.sun.jna.Pointer

String DLLPATH=args[0]
String talk=args[1]
println "DLLPATH=$DLLPATH"
println "talk=$talk"

/**
 * AquesTalkDa.dll のラッパー
 */
NativeLibrary.addSearchPath("AquesTalkDa",DLLPATH)
interface Aques extends Library {
	//dllのロード
	Aques INSTANCE = Native.loadLibrary("AquesTalkDa", Aques.class)
	// ハンドラの作成
	Pointer AquesTalkDa_Create()
	// 開放
	void AquesTalkDa_Release(Pointer hMe)
	// 再生（同期実行）
	int AquesTalkDa_PlaySync(String koe, int iSpeed)
	// 再生（非同期実行）
	Pointer AquesTalkDa_Play(Pointer hMe, String koe, int iSpeed, Pointer hWnd, int msg,int dwUser)
	// 再生中かどうか
	int AquesTalkDa_IsPlay(Pointer hMe)
}

// ハンドラの作成
Pointer data = Aques.INSTANCE.AquesTalkDa_Create()

// ゆっくりしてく
Aques.INSTANCE.AquesTalkDa_Play(data, talk, 100, null, 0, 0)
Thread.sleep(6000)

// ハンドラの開放
Aques.INSTANCE.AquesTalkDa_Release(data)

