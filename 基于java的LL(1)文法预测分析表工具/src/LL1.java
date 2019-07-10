import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
public class LL1 extends Application{		
	public static List<String> nonterminator = new ArrayList<String>();
	public static List<String> terminator = new ArrayList<String>();
	public static List<String> oldList = new ArrayList<String>();
	public static List<String> oldListItems = new ArrayList<String>();
	public static List<String> newList = new ArrayList<String>();
	public static Map<String, List<String>> firstList = new HashMap<String, List<String>>();
	public static Map<String, List<String>> followList = new HashMap<String, List<String>>();
	static TextArea filename=new TextArea();
	private static Label status=new Label();
	static TextArea ta=new TextArea();
	@Override
    public void start(Stage primaryStage) throws Exception {
		
        primaryStage.setTitle("构照LL(1)文法的预测分析表的工具");
        Button btn = new Button("提交");
        Button btn0 = new Button("输出文件中的文法");
        Button btn1 = new Button("消除左递归");
        Button btn2 = new Button("求非终结符");
        Button btn3 = new Button("求终结符");
        Button btn4 = new Button("求first集");
        Button btn5 = new Button("求follow集");
        Button btn6 = new Button("判断是否LL1文法");
        Button btn7 = new Button("生成预测分析表");
        
        filename.setPrefColumnCount(12);
        filename.setPrefRowCount(7);
        filename.setWrapText(true);
        ta.setPrefColumnCount(50);
        ta.setPrefRowCount(10);
        ta.setWrapText(true);
        
        VBox hbox1=new VBox(2);
        hbox1.getChildren().addAll(new Label("请输入文法"),filename,btn);
        HBox hbox2=new HBox(3);
        hbox2.getChildren().addAll(btn0,btn1);
        HBox hbox3=new HBox(3);
        hbox3.getChildren().addAll(btn2,btn3);
        HBox hbox4=new HBox(2);
        hbox4.getChildren().addAll(btn4,btn5);
        HBox hbox5=new HBox(2);
        hbox5.getChildren().addAll(btn6,btn7);
        VBox vbox=new VBox(6);
        vbox.getChildren().addAll(new Label(),hbox2,hbox3,hbox4,hbox5,status);
        BorderPane pane=new BorderPane();
        
        pane.setLeft(hbox1);
        pane.setCenter(vbox);
        pane.setBottom(ta);
        btn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {	
            	ta.setText("");
						play5();
            }
        });
        btn0.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
            	if(oldListItems!=null&&!oldListItems.isEmpty())
            	ta.appendText("文法如下："+oldListItems.toString()+"\n");
            	else ta.setText("文法输入有误，请重新输入！");
            }
        });
        btn1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	if(oldListItems!=null&&!oldListItems.isEmpty())
            	ta.appendText("消除左递归后的文法如下："+newList.toString()+"\n");
            	else ta.setText("文法输入有误，请重新输入！");
            }
        });
        
        btn2.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
            	if(oldListItems!=null&&!oldListItems.isEmpty())
            	ta.appendText("消除左递归后的文法的非终结符号如下："+nonterminator.toString()+"\n");
            	else ta.setText("文法输入有误，请重新输入！");
            }
        });
        btn3.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
            	if(oldListItems!=null&&!oldListItems.isEmpty())
            	ta.appendText("消除左递归后的文法的终结符号如下："+terminator.toString()+"\n");
            	else ta.setText("文法输入有误，请重新输入！");
            }
        });
        btn4.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
            	if(oldListItems!=null&&!oldListItems.isEmpty())
            	ta.appendText("first集如下："+firstList.toString()+"\n");
            	else ta.setText("文法输入有误，请重新输入！");
            }
        });
        btn5.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
            	if(oldListItems!=null&&!oldListItems.isEmpty())
            	ta.appendText("follow集如下："+followList.toString()+"\n");
            	else ta.setText("文法输入有误，请重新输入！");
            }
        });
        btn6.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
            	if(oldListItems!=null&&!oldListItems.isEmpty())
            	ta.appendText("该文法是LL（1）文法吗？"+ isLL1()+"\n");
            	else ta.setText("文法输入有误，请重新输入！");
            }
        });
        btn7.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
            	if(oldListItems!=null&&!oldListItems.isEmpty()){
            	if (isLL1()) {
        			getLL1();
        		}
            	}
            	else ta.setText("文法输入有误，请重新输入！");
            	
            }
        });
        StackPane root = new StackPane();
        root.getChildren().add(pane);
        primaryStage.setScene(new Scene(root, 530, 350));

        primaryStage.show();
    }
	

	public static void getGrammar() {
		String readline=filename.getText().toString();
		String[] grammar = readline.split(" |\n");
		for (int i = 0; i < grammar.length; i++) {
			oldList.add(grammar[i]);
			String[] items = grammar[i].split(">|\\|");// "\\|"就是表示"|"
			for (int k = 1; k < items.length; k++)
			{
				//if(items[k].length()>=4&&items[k].contains("->"))
				oldListItems.add(grammar[i].charAt(0) + "-->" + items[k]);
				
		
			}
		}
	}

	public static boolean isContain(String str) {
		for (int i = 0; i < nonterminator.size(); i++) {
			if (nonterminator.get(i).equals(str))
				return true;
		}
		return false;
	}

	public static void getNonterminator(String grammarItems) {		
		String[] string = grammarItems.split("\n");
		for (int i = 0; i < string.length; i++) {
			int index = string[i].indexOf("-");
			String str = string[i].substring(0, index);
				if (!isContain(str)) {
					nonterminator.add(str);
			}
		}
	}

	public static void getTerminator() {
		for (int i = 0; i < newList.size(); i++) {
			int index = newList.get(i).indexOf('>') + 1;
			String str = newList.get(i).substring(index);
			for (int k = 0; k < str.length(); k++) {
				if (!isNonterminator(""+str.charAt(k))) {
					if (str.charAt(k) != '’' && str.charAt(k) != '~') {
						if (!isContain("" + str.charAt(k))) {
							terminator.add("" + str.charAt(k));
						}
					}
				}
			}
		}
		terminator.add("#");
	}

	public static boolean isNonterminator(String ch) {
		for (int i = 0; i < nonterminator.size(); i++) {
			if (ch.equals(nonterminator.get(i))) {
				return true;
			}
		}
		return false;
	}
	
	public static void eliminateLeftRecursion() {
		
		for (int i = 0; i < oldListItems.size(); i++) {
			if (oldListItems.get(i).charAt(0) == oldListItems.get(i).charAt(4)) {
				for (int j = 0; j < oldListItems.size(); j++) {
					
					if (oldListItems.get(j).startsWith("" + oldListItems.get(i).charAt(0))&& oldListItems.get(j).length() == 5) {
						if (!isContain(newList,"" + oldListItems.get(i).charAt(0) + "-->"+ oldListItems.get(j).charAt(4)+ oldListItems.get(i).charAt(0) + "’")) {
						newList.add(oldListItems.get(i).charAt(0) + "-->"+ oldListItems.get(j).charAt(4)+ oldListItems.get(i).charAt(0) + "’");
						}
						newList.add(oldListItems.get(i).charAt(0)+ "’"+ "-->"+ oldListItems.get(i).substring(5)+ oldListItems.get(i).charAt(0) + "’");
						if (!isContain(newList,"" + oldListItems.get(i).charAt(0) + "’" + "-->"+ "~")) {
						newList.add(oldListItems.get(i).charAt(0) + "’" + "-->"+ "~");
						}
					}
				}
			} else {
				int flag = 0;
				for (int k = 0; k < newList.size(); k++) {
					if (newList.get(k).contains(oldListItems.get(i))) {
						flag = 1;
						break;
					}
				}
				if (flag == 0) {
					newList.add(oldListItems.get(i));
				}
			}
		}
	}

	public static void getOneFirst(String vnItem,List<String> First) {
		for (int i = 0; i < newList.size(); i++) {
			if (vnItem.length() == 1) {
				if (newList.get(i).startsWith(vnItem)
						&& newList.get(i).charAt(1) == '-') {
					int index = newList.get(i).indexOf('>') + 1;
					if (isNonterminator(""+newList.get(i).charAt(index))) {
						getOneFirst("" + newList.get(i).charAt(index),First);
					} else {
						if (!isContain(First,"" + newList.get(i).charAt(index))) {
							First.add("" + newList.get(i).charAt(index));
						}

					}
				}
			} else {
				if (newList.get(i).startsWith(vnItem)) {
					int index = newList.get(i).indexOf('>') + 1;
					if (isNonterminator(""+newList.get(i).charAt(index))) {
						getOneFirst("" + newList.get(i).charAt(index),First);
					} else {
						if (!isContain(First,"" + newList.get(i).charAt(index))) {
							First.add("" + newList.get(i).charAt(index));
						}
					}
				}
			}
		}
	}
	
	public static void getFirst() {
		for (int i = 0; i < nonterminator.size(); i++) {
			List<String> First = new ArrayList<String>();
			getOneFirst(nonterminator.get(i),First);
			firstList.put(nonterminator.get(i), First);
		}
	}

	public static boolean isContainNull(List<String> list) {
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).equals("~")) {
				return true;
			}
		}
		return false;
	}

	public static boolean isContain(List<String> list,String str) {
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).equals(str)) {
				return true;
			}
		}
		return false;
	}
	public static List<String> getItemFirst(String str) {
		List<String> list = new ArrayList<String>();
		if (isNonterminator(""+str.charAt(0))) {
			if (str.length() > 1) {
				if (str.charAt(1) != '’') {
					list.addAll(firstList.get("" + str.charAt(0)));
				} else {
					list.addAll(firstList.get("" + str.charAt(0) + str.charAt(1)));
				}
			}
		} else {
			list.add("" + str.charAt(0));
		}
		return list;
	}
	
	public static void getOneFollow(String vnItem,List<String> Follow) {
		if (vnItem.equals(nonterminator.get(0))) {
			if (!isContain(Follow,"#")) {
				Follow.add("#");
			}
		}
		for (int i = 0; i < newList.size(); i++) {
			int index1 = newList.get(i).indexOf(">") + 1;
			String str = newList.get(i).substring(index1);
			if (vnItem.length() == 1) {
				// 判断E类型的
				if (str.contains(vnItem)&& str.charAt(str.indexOf(vnItem.charAt(0)) + 1) != '’') {
					if (str.indexOf(vnItem.charAt(0)) != str.length()) {
						String temp = str.substring(str.indexOf(vnItem.charAt(0)) + 1);
						StringBuilder stringBuilder = new StringBuilder();
						stringBuilder.append(temp);
						for (int k = stringBuilder.length(); k > 1; k--) {
							if (isNonterminator(stringBuilder.toString())) {
								break;
							} else {
								int length = stringBuilder.length();
								stringBuilder.deleteCharAt(length - 1);
							}
						}
						if (stringBuilder.length() > 0) {
							if (isNonterminator(""+stringBuilder.charAt(0))) {
								if (firstList.containsKey(stringBuilder.toString())) {
									List<String> list = firstList.get(stringBuilder.toString());
									for (int q = 0; q < list.size(); q++) {
										if (!list.get(q).equals("~")) {
											if (!isContain(Follow, list.get(q))) {
												Follow.add(list.get(q));
											}
										}
									}
								}
							} else {
								if (!isContain(Follow, stringBuilder.toString())) {
									Follow.add(stringBuilder.toString());
								}
							}
						}
					}
				}
			} else {
				// 判断E’类型的
				if (str.contains(vnItem)) {
					int index = str.indexOf(vnItem);
					String left = str.substring(index + vnItem.length(),
							str.length());
					StringBuilder stringBuilder = new StringBuilder();
					stringBuilder.append(left);
					for (int k = stringBuilder.length(); k > 1; k--) {
						if (isNonterminator(stringBuilder.toString())) {
							break;
						} else {
							int length = stringBuilder.length();
							stringBuilder.deleteCharAt(length);
						}
					}
					if (stringBuilder.length() > 0) {
						if (isNonterminator(""+stringBuilder.charAt(0))) {
							if (firstList.containsKey(stringBuilder.toString())) {
								List<String> list = firstList.get(stringBuilder.toString());
								for (int q = 0; q < list.size(); q++) {
									if (!list.get(q).equals("~")) {
										if (!isContain(Follow, list.get(q))) {
											Follow.add(list.get(q));
										}
									}
								}
							}
						} else {
							if (!isContain(Follow, stringBuilder.toString())) {
								Follow.add(stringBuilder.toString());
							}
						}
					}
				}
			}

		}
		for (int i = 0; i < newList.size(); i++) {
			int index1 = newList.get(i).indexOf(">") + 1;
			String str = newList.get(i).substring(index1);
			if (vnItem.length() == 1) {
				if (str.contains(vnItem)) {
					if (str.indexOf(vnItem) == str.length()&& str.charAt(str.indexOf(vnItem.charAt(0)) + 1) != '’') {
						String s = newList.get(i).substring(0,newList.get(i).indexOf("-"));
						getOneFollow(s,Follow);// T-->FT’,要求T'先求T
					} else {
						String left = str.substring(str.indexOf(vnItem) + 1);
						StringBuilder stringBuilder = new StringBuilder();
						stringBuilder.append(left);
						for (int k = stringBuilder.length(); k > 1; k--) {
							if (isNonterminator(stringBuilder.toString())) {
								break;
							} else {
								int length = stringBuilder.length();
								stringBuilder.deleteCharAt(length - 1);
							}
						}
						if (isNonterminator(stringBuilder.toString())) {
							if (firstList.containsKey(stringBuilder.toString())) {
								List<String> temp = firstList.get(stringBuilder.toString());
								if (isContainNull(temp)) {
									String s = newList.get(i).substring(0,newList.get(i).indexOf("-"));
									getOneFollow(s,Follow);
								}
							}
						}
					}
				}

			} else {
				if (str.contains(vnItem)) {
					String st = newList.get(i);
					if (st.endsWith(vnItem)) {
						if (!st.startsWith(vnItem)) {
							String s = newList.get(i).substring(0,
									newList.get(i).indexOf("-"));
							getOneFollow(s,Follow);
						}
					} else {
						String left = str.substring(str.indexOf(vnItem) + 2);
						StringBuilder stringBuilder = new StringBuilder();
						stringBuilder.append(left);
						for (int k = stringBuilder.length(); k > 1; k--) {
							if (isNonterminator(stringBuilder.toString())) {
								break;
							} else {
								int length = stringBuilder.length();
								stringBuilder.deleteCharAt(length - 1);
							}
						}
						if (isNonterminator(stringBuilder.toString())) {
							if (firstList.containsKey(stringBuilder.toString())) {
								List<String> temp = firstList.get(stringBuilder
										.toString());
								if (isContainNull(temp)) {
									String s = newList.get(i).substring(0,
											newList.get(i).indexOf("-"));
									getOneFollow(s,Follow);
								}
							}
						}
					}
				}
			}
		}

	}

	public static void getFollow() {
		for (int i = 0; i < nonterminator.size(); i++) {
			List<String> Follow = new ArrayList<String>();
			getOneFollow(nonterminator.get(i),Follow);
			followList.put(nonterminator.get(i), Follow);
		}
	}

	public static boolean isLL1() {

		for (int i = 0; i < nonterminator.size(); i++) {
			StringBuilder stringBuilder = new StringBuilder();
			for (int j = 0; j < newList.size(); j++) {
				if (newList.get(j).startsWith(nonterminator.get(i) + "-->")) {
					String str = newList.get(j).substring(newList.get(j).indexOf(">") + 1);
					stringBuilder.append(str + " ");
				}
			}
			String[] items = stringBuilder.toString().split(" ");
			if (items.length > 1) {
					List<String> first = getItemFirst(items[0]);
					for (int n =1; n < items.length; n++) {
						List<String> second = getItemFirst(items[n]);
						first.retainAll(second);//first(a1)与first(a2)不相交
						if (first.size() != 0) {
							return false;
						}
					}				
			}
		}
		
		for (int i = 0; i < nonterminator.size(); i++) {
			if (firstList.get(nonterminator.get(i)).contains("~")) {
				List<String> first = new ArrayList<String>();
				first.addAll(firstList.get(nonterminator.get(i)));
				List<String> follow = new ArrayList<String>();
				follow.addAll(followList.get(nonterminator.get(i)));
				first.retainAll(follow);//first(a)与follow(a)不相交
				if (first.size() != 0) {
					return false;
				}
			}
		}
		
		return true;
	}

	public static String findLink(String vtItem, String vnItem) {
		for (int i = 0; i < newList.size(); i++) {
			if (vnItem.length() == 1) {
				if (newList.get(i).startsWith(vnItem)&& newList.get(i).charAt(1) != '’') {
					int index = newList.get(i).indexOf(">") + 1;
					String str = newList.get(i).substring(index);
					if (isNonterminator(""+str.charAt(0))) {
						StringBuilder stringBuilder = new StringBuilder();
						stringBuilder.append(str);
						for (int k = stringBuilder.length(); k > 1; k--) {
							if (isNonterminator(stringBuilder.toString())) {
								break;
							} else {
								int length = stringBuilder.length();
								stringBuilder.deleteCharAt(length - 1);
							}
						}
						if (firstList.get(stringBuilder.toString()).contains(vtItem)) return newList.get(i);						
					} else {
						if (vtItem.equals("" + str.charAt(0))) return newList.get(i);
					}
				}
			} else {
				if (newList.get(i).startsWith(vnItem)) {
					int index = newList.get(i).indexOf(">") + 1;
					String str = newList.get(i).substring(index);
					if (isNonterminator(""+str.charAt(0))) {
						StringBuilder stringBuilder = new StringBuilder();
						stringBuilder.append(str);
						for (int k = stringBuilder.length(); k > 1; k--) {
							if (isNonterminator(stringBuilder.toString())) {
								break;
							} else {
								int length = stringBuilder.length();
								stringBuilder.deleteCharAt(length - 1);
							}
						}
						if (firstList.get(stringBuilder.toString()).contains(vtItem))return newList.get(i);
					} else {
						if (vtItem.equals("" + str.charAt(0))) return newList.get(i);	
					}
				}
			}
		}
		return null;
	}
	
	public static void getLL1() {
		
		for (int i = 0; i < terminator.size(); i++) {
			ta.appendText("\t\t"+terminator.get(i));
		}
		ta.appendText("\n");
		for (int i = 0; i < nonterminator.size(); i++) {
			ta.appendText(nonterminator.get(i)+"\t\t");
			for (int k = 0; k < terminator.size(); k++) {
				String str = findLink(""+ terminator.get(k), "" + nonterminator.get(i));
				if (str != null) {
					ta.appendText(str+ "     ");
				} else {
					if (!firstList.get(nonterminator.get(i)).contains("" + terminator.get(k))&& firstList.get(nonterminator.get(i)).contains("~")) {
						if (followList.get(nonterminator.get(i)).contains("" + terminator.get(k))) {
							ta.appendText(nonterminator.get(i) + "-->~" + "    	");
						} else {
							ta.appendText(" \t\t");
						}
					} else {
						ta.appendText(" \t\t");
					}
				}
			}
			ta.appendText("\n");
		}
	}

	public static void play5() {
		nonterminator.clear();
		terminator.clear();
		oldList.clear();
		oldListItems.clear();
		newList.clear();
		firstList.clear();
		followList.clear();
		getGrammar();
		eliminateLeftRecursion();
		StringBuilder stringBuilder = new StringBuilder();
		for (int i = 0; i < newList.size(); i++) {
			stringBuilder.append(newList.get(i) + "\n");
		}
		getNonterminator(stringBuilder.toString());
		getTerminator();
		getFirst();
		getFollow();
		
	}
	public static void main(String[] args) {
		
		launch(args);
		
	}
}
