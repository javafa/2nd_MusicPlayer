# MusicPlayer
뮤직 플레이어

## Permission
* READ_EXTERNAL_STORAGE : 음악 데이터는 외부저장소를 읽는 런타임 권한이 필요하다.
* manifest 에도 같이 정의해야만 한다.

## Content Resolver
```java
    // 1. 음악 데이터의 Content Uri
    Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;

    // 2. 데이터 컬럼
    String proj[] = {
            MediaStore.Audio.Media._ID,     // 음원 아이디
            MediaStore.Audio.Media.ALBUM_ID,// 앨범 아이디 : 앨범이미지를 가져올때 사용
            MediaStore.Audio.Media.TITLE,   // 제목
            MediaStore.Audio.Media.ARTIST   // 가수
    };

```
## Media Player
```java
    // 1. 미디어 플레이어 사용하기
    MediaPlayer player = MediaPlayer.create(context, 음원Uri);

    // 2. 설정
    player.setLooping(false); // 반복여부

    // 3. 완료 리스너
    player.setOnCompletionListener(listener);

    // 4. 시작
    player.start();

    // 5. 일시정지
    player.pause();

    // 6. 종료
    player.stop();

    // 7. 해제
    player.release();
```
