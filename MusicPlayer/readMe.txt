��������(���)
1. ButterKnife(2017.08.15)




1. Ŀ�꣺��һ���¿ṷ���ֵ�APP

2017.05.09



1. �����TestActivity������һ���򵥵Ĳ��ԡ�

2017.05.16



1. ����Service��
 (1):onstart()������onStartCommand()����������
    onstart()��������android2.0һ�µİ汾��ʹ�á�����android2.0������ʹ��onstartCommand()����������������������һ��ʹ��ʱ�����������ͻ��
 
(2):onStartComandʹ��ʱ�����ص���һ��(int)���Ρ�
    ������ο������ĸ�����ֵ��start_sticky��start_no_sticky��START_REDELIVER_INTENT��START_STICKY_COMPATIBILITY��
    ���ǵĺ���ֱ��ǣ�
       1):START_STICKY�����service���̱�kill��������service��״̬Ϊ��ʼ״̬�������������͵�intent�������ϵͳ�᳢�����´���service�����ڷ���״̬Ϊ��ʼ״̬�����Դ��������һ�������onStartCommand(Intent,int,int)����������ڴ��ڼ�û���κ�����������ݵ�service����ô����Intent��Ϊnull��
       2):START_NOT_STICKY������ճ�Եġ���ʹ���������ֵʱ�������ִ����onStartCommand�󣬷����쳣kill����ϵͳ�����Զ������÷���
       3):START_REDELIVER_INTENT���ش�Intent��ʹ���������ֵʱ�������ִ����onStartCommand�󣬷����쳣kill����ϵͳ���Զ������÷��񣬲���Intent��ֵ���롣
      4):START_STICKY_COMPATIBILITY��START_STICKY�ļ��ݰ汾��������֤����kill��һ����������
 
(3)intentservice���÷�
  Android��Service����ʱ��ͨ��Looper��Thread�������׼Service�д����߼����������⡣��android�У�Service��activity��������ͬһ�����߳��еġ������е�service�������˯��5��ʱ��activityҲ�ᵱ����
  IntenService��service�����࣬���������첽���󡣿ͻ���ͨ��startService(Intent)�������������IntentService��IntentServiceͨ��worker thread����ÿ��intent����ִ������Զ�ֹͣService��
  intentService��Ҫʵ������������һ�����췽������дһ��onHandleIntent()������



217.05.21



1. ����˻����Ĳ�ѯ�������˼򵥵�����������ʾ����֤����Ļ����߼���Ч��

2. �ڳ������еĵ�һ���ͱ����ˣ�һֱ��ʾ�����ݿ��ж�ȡʧ�ܣ����˺þ�û�з������⣬fuck������

2017.06.12


1. ��MusicUtil.java���޸��˸տ�ʼ��ȡ���ݾͻ���������⣬����Ϊ��getGlobalMusicList()�����н�URIд����MediaStore.Audio.Albums.EXTERNAL_CONTENT_URI����Ӧ����MediaStore.Audio.Media.INTERNAL_CONTENT_URI��

2. ��MusicUtil.java���޸��˱���Cursorʱ�����bug����getMusicList()�����н�cursor.moveToNext()����forѭ���ڵ���󼴿ɡ�

3. ����˻��������ݶ�ȡ������������TestActivity2.java�н����˼򵥵���֤��

2017.06.25


1. �����MainView.java�Ĵ��벿�֣���û�в��ԡ�

2017.07.08



1. �����MainView.java�Ĳ��ԣ�����һ�������⡣
2. ���������ViewPaper�͡�����Fragment�е�ViewPaper������ViewPaper��ScrollView�Ļ����¼���ͻ�ˣ������û�н����

2017.07.09


1. �����smoothScrollTo()������Ч�����⣬�����ǽ�smoothScrollTo()������this.post()����������ʲôԭ�����ڻ��������

2017.07.12



1. ��Ҫ��MainActivity.java��д�����˵����и����������¼������̣����˸������ļ��ӡ�����com.fzq.musicplayer.ui.leftmenu���ж�����ص�Activity��

2017.08.15



1. ������com.fzq.musicplayer.ui.topfrags.LocalMusicActivity.java���棬ʹ��֮ǰ��activity_local.xml���������ListView��ΪRecyclerView��

2017.08.18


1. ��Ҫд�����������е�������ֿ�󣬽��뱾�����ֽ�����ĸ�Fragment������ֻ�ѵ�����Fragment��������ʾ�����ˡ�

2017.08.19


1. ��Ҫ���Բ�ʵ����com.fzq.musicplayer.ui.fragments.localmusic.DanQuAdapter��Item�ж���ؼ��ĵ���¼���Ŀǰ��Ϊ�ǱȽϺõ�һ��ʵ�ַ�ʽ��

2017.09.03



1. �¼������ڲ��ŵĽ��棺PlayingActivity.java��

2. ����˲�����ص��߼���

2017.09.16



1. ���ŵ�ʱ����־��ӡ���߼�����û�����⣬�����ǲ���û��������ԭ����������AndroidManifest�ļ�������MusicService.java�ˣ�fuck����������

2017.09.17