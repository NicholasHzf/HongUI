# HongUI
Create this HongUI repository just for personal interest. 

**Add the JitPack repository to your build file**
- Gradle:
```java
allprojects {
	repositories {
		...
		maven { url 'https://jitpack.io' }
	}
}

dependencies {
	implementation 'com.github.NicholasHzf:HongUI:2.0.0'
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
	<version>2.0.0</version>
</dependency>
```
---

[CSDN Blog](https://blog.csdn.net/Nicholas1hzf/article/details/104377358)
- 2020.2.18 version:1.1.0 更新：新增自动补全功能，修改了点击事件的参数，添加截图
- 2020.3.30 version:2.0.0 更新：新增周历展示控件，圆点控件，添加截图

---

## a circle dot widget
##### 一个圆点控件，可用于无序列表的圆点，或者可选择的一横排圆点

**screenshot**

![GitHub](https://github.com/NicholasHzf/HongUI/raw/master/screenshot/circle_dot.png) 

**How to use:**

**Step 1. Add the JitPack repository to your build file**
**Step 2. Use it in your own layout file:**

|attr|default  |mean |
|--|--|--|
|app:inner_color  |#ff0000  |圆内部颜色|
|app:stroke_color  |#0000ff  |圆边沿颜色，需设置able_select为true|
|app:able_select  |false |能否选中|
|app:selected  |false |是否选中|
|app:radius  |12  |圆的半径|
|app:stroke_ratio  |0.02  |边线占半径的比例|

```xml
<com.hzf.nicholas.custom_ui.circle_dot.CircleDot
    android:id="@+id/circle_dot"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:layout_margin="14dp"
    app:stroke_color="#ff0000"
    app:inner_color="#1089ee"
    app:able_select="true"
	app:selected="true"
    app:radius="50"
    app:stroke_ratio="0.2"/>
```
**Step 3. Use it in your own java code:**
```java
//获取控件
mCircleDot = findViewById(R.id.circle_dot);
//设置能否选中
mCircleDot.setAbleSelect(true);
设置内部颜色
mCircleDot.setColorStringInner("#1089EE");
//设置边沿颜色
mCircleDot.setColorStringStroke("#ff0000");
//设置半径
mCircleDot.setRadius(24);
//设置边线占半径的比例
mCircleDot.setStrokeRatio(0.2f);
//设置选中状态
mCircleDot.setSelected(true);
//设置点击事件
mCircleDot.setOnClickListener(new CircleDot.OnClickListener() {
    @Override
    public void onClick() {
        Toast.makeText(CircleDotActivity.this,"点击了CircleDot!",Toast.LENGTH_SHORT).show();
    }
});
```

## a widget used to display week info 
##### 一个用于展示当前一周或最近七天的周历

**screenshot**

![GitHub](https://github.com/NicholasHzf/HongUI/raw/master/screenshot/weekly_display.png) 

**How to use:**

**Step 1. Add the JitPack repository to your build file**
**Step 2. Use it in your own layout file:**

|attr|default  |mean |
|--|--|--|
|app:bg_color  |Color.WHITE(未选中) Color.BLUE(选中)  |item的圆角背景，可用selector|
|app:text_day_color  |Color.BLUE(未选中) Color.WHITE(选中)  |星期的文字颜色，可用selector|
|app:text_date_color  |Color.BLUE(未选中) Color.WHITE(选中)  |日期的文字颜色，可用selector|
|app:dot_color  |Color.YELLOW  |提示点颜色|
|app:with_data  |false  |不带提示点|
|app:padding_day_lr  |0  |圆角矩形左右内边距|
|app:padding_day_tb  |0  |圆角矩形上下内边距|
|app:padding_date_dot  |0  |提示点与圆角矩形内边距|
|app:mode  |MODE_NORMAL_THIS_WEEK  |6种模式|
```xml
<enum name = "MODE_NORMAL_THIS_WEEK" value="0" />
<enum name = "MODE_ENGLISH_THREE_THIS_WEEK" value="1" />
<enum name = "MODE_ENGLISH_SINGLE_THIS_WEEK" value="2" />
<enum name = "MODE_NORMAL" value="3" />
<enum name = "MODE_ENGLISH_THREE" value="4" />
<enum name = "MODE_ENGLISH_SINGLE" value="5" />
```
```xml
<com.hzf.nicholas.custom_ui.week_display.WeekDisplay
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:layout_marginTop="14dp"
    android:layout_marginLeft="14dp"
    android:layout_marginRight="14dp"
    app:padding_day_lr="20dp"
    app:padding_day_tb="30dp"
    app:bg_color="@drawable/selector_week_display_bg"
    app:text_date_color="@drawable/selector_week_display_date"
    app:text_day_color="@drawable/selector_week_display_day"
    app:mode="MODE_ENGLISH_SINGLE"/>
```
**Step 3. Use it in your own java code:**
```java
//获取控件
WeekDisplay weekDisplay = findViewById(R.id.second);
//设置提示点颜色
weekDisplay.setDotColor(R.color.fc_blue_for_label);
//设置模式
weekDisplay.setMode(2);
//设置数据（至多三个提示点，data为长度是七的int数组，值 0 无 1 一个提示点 2 两个提示点 3|>3 三个提示点）
weekDisplay.setData(data);
//设置点击事件
weekDisplay.setOnItemClickListener(new WeekDisplay.OnItemClickListener() {
	@Override
    public void onClick(int position, String day, String date) {
        Toast.makeText(WeeklyDisplayActivity.this,"onClick"+position,Toast.LENGTH_SHORT).show();
    }
});
```

## a search widget with history records 
##### 一个带有历史记录的搜索框

**screenshot**

![GitHub](https://github.com/NicholasHzf/HongUI/raw/master/screenshot/search_widget.jpg) 

**How to use:**

**Step 1. Add the JitPack repository to your build file**
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
