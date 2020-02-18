# HongUI
Create this HongUI repository just for personal interest. 

---

## a search widget with history records 
##### 一个带有历史记录的搜索框

- 2020.2.18 version:1.1.0 更新：新增自动补全功能，修改了点击事件的参数，添加截图

**screenshot**
![](https://github.com/NicholasHzf/HongUI/raw/master/screenshot/search_widget_1) 
![](https://github.com/NicholasHzf/HongUI/raw/master/screenshot/search_widget_2) 
![](https://github.com/NicholasHzf/HongUI/raw/master/screenshot/search_widget_3) 
![](https://github.com/NicholasHzf/HongUI/raw/master/screenshot/search_widget_4) 

**How to use:**

**Step 1. Add the JitPack repository to your build file**
- Gradle:
```java
allprojects {
	repositories {
		...
		maven { url 'https://jitpack.io' }
	}
}

dependencies {
	implementation 'com.github.NicholasHzf:HongUI:1.1.0'
}
```
- maven
```xml
<repositories>
	<repository>
		<id>jitpack.io</id>
		<url>https://jitpack.io</url>
	</repository>
</repositories>

<dependency>
	<groupId>com.github.NicholasHzf</groupId>
	<artifactId>HongUI</artifactId>
	<version>1.1.0</version>
</dependency>
```
**Step 2. Use it in your own layout file:**
|attr|default  |mean |
|--|--|--|
|app:history  |历史记录  |历史记录标签标题|
|app:title_hint  |搜索  |搜索框提示文字|
|app:left_icon_iv  |搜索图标  |搜索图标|
|app:right_icon_iv  |语音图标  |语音图标|
|app:empty_icon_iv  |清空图标  |清空图标|
```xml
<com.hzf.nicholas.custom_ui.SearchWidget
android:id="@+id/search_widget"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    app:history="History Record"
    app:title_hint="hint text" />
```
**Step 3. Use it in your own java code:**
```java
//设置搜索框提示信息
mSearchWidget.setSearchHint("修改搜索框默认提示信息");
//设置搜索框历史记录标题
mSearchWidget.setHistoryTVText("修改搜索历史记录标题");
//设置点击事件
mSearchWidget.setOnIconClickListener(new SearchWidget.OnIconClickListener() {
    @Override
    public void onRightClick() {
        Toast.makeText(SearchWidgetActivity.this,"语音暂未实现！",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onHistoryRecordClick(int position) {
        String content = SearchRecordLab.get(SearchWidgetActivity.this).getSearchRecords().get(position).getContent();
        Intent intent = new Intent(SearchWidgetActivity.this,TestActivity.class);
        intent.putExtra("INFO",content);
        startActivity(intent);
        Toast.makeText(SearchWidgetActivity.this,"点击："+content,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSearchAction(String content) {
        Intent intent = new Intent(SearchWidgetActivity.this,TestActivity.class);
        intent.putExtra("INFO",content);
        startActivity(intent);
        Toast.makeText(SearchWidgetActivity.this,"点击键盘搜索！"+content,Toast.LENGTH_SHORT).show();
    }
});
```

# License
---

```
Copyright 2020-2-17 HongZhiFu

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

	 http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
