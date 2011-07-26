
Slideshare: http://www.slideshare.net/kimukou/hudson-using-groovy

手順＞

[1]Hudson環境構築
  1)LTHudsonフォルダを作成
  2)LTHudson/hudson-mst を作成
  3)hudson.war をダウンロード( http://hudson-ci.org/ )
  4)hudson.bat、hudson-cli.bat をコピー
  5)hudson.batを実行 => HUDSON_HOME/warに解凍される
  6)HUDSON_HOME/war/WEB-INF/lib
      groovy-all-1.6.0.jar => groovy-all-1.7.5.jar
    に差し替え
  7)残りの設定ファイルを上書きしてください
    jobs
    plugin
    script

[2]GroovyConsole単体環境構築
  1)LTHudsonフォルダを作成
  2)LTHudson/standalone をコピー
  3)http://d.hatena.ne.jp/bluepapa32/20101013/1286977550 右下の
    Groovy Console
      http://dl.getdropbox.com/u/653108/groovy/console.jnlp をクリック
  4)信頼してインストール
  5)gcon_jnatest.groovy をD&Dしてください
  6)Ctrl+R で実行

---------------------------------------------------------------
Procedure>

[1]Hudson environment construction
  1)I make LTHudson folder
  2)I make LTHudson/hudson-mst
  3)I download hudson.war (http://hudson-ci.org/)
  4)I copy hudson.bat, hudson-cli.bat
  5)It is practice = in hudson.bat> It is defrosted by HUDSON_HOME/war
  6)HUDSON_HOME/war/WEB-INF/lib
    It replaces it with 
    groovy-all-1.6.0.jar => groovy-all-1.7.5.jar. 
  7)Please overwrite with the remaining configuration file
    jobs
    plugin
    script

[2]GroovyConsole simple substance environment construction
  1)I make LTHudson folder
  2)I copy LTHudson/standalone
  3)Of the http://d.hatena.ne.jp/bluepapa32/20101013/1286977550 lower right
    Groovy Console
      I click http://dl.getdropbox.com/u/653108/groovy/console.jnlp
  4)I trust it and install it
  5)D&D please do gcon_jnatest.groovy
  6)I carry it out in Ctrl+R

