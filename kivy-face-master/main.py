# coding: utf-8
#qpy:kivy
import sys
reload(sys)
sys.setdefaultencoding('utf8')
'''defaultencoding = 'ISO-8859-1'
if sys.getdefaultencoding() != defaultencoding:
    reload(sys)
    sys.setdefaultencoding(defaultencoding)'''
import os
pyhome=os.popen('echo $PYTHONHOME').read().strip()
opencv3=os.path.join(pyhome,'lib/python2.7/site-packages/cv2.py')
if not os.path.exists(opencv3):
    command="echo 'from cv.cv2 import *'>%s"%opencv3
    os.system(command)
import traceback
import time
import sqlite3
import math
import kivy
import cv2
import numpy as np
import count as mainCount
from table import TableView, TableColumn
from kivy.app import App
from kivy.clock import Clock
from kivy.uix.boxlayout import BoxLayout
from kivy.properties import ObjectProperty
from kivy.uix.camera import Camera
from kivy.graphics.texture import Texture
from kivy.uix.carousel import Carousel
from kivy.uix.button import Button
from kivy.uix.textinput import TextInput
from kivy.uix.label import Label
from kivy.core.window import Window
from kivy.utils import get_color_from_hex
from kivy.resources import resource_add_path,resource_find
resource_add_path(os.path.abspath('./'))
from kivy.core.text import LabelBase
LabelBase.register('Roboto','droid.ttf')
#face_cascade=cv2.CascadeClassifier(r'./cascades/haarcascade_frontalface_default.xml')
face_cascade=cv2.CascadeClassifier(r'./cascades/lbpcascade_frontalface.xml')
#face_cascade=cv2.CascadeClassifier(r'./cascades/lbpcascade_frontalcatface.xml')
face_cascade_improved=cv2.CascadeClassifier(r'./cascades/lbpcascade_frontalface_improved.xml')
face_cascade_pro=cv2.CascadeClassifier(r'./cascades/lbpcascade_profileface.xml')
eye_cascade = cv2.CascadeClassifier(r'./cascades/haarcascade_eye.xml')
cv2.setUseOptimized(True)

class CV2mera(Camera):
    def __init__(self, **kwargs):
        self._camera = None
        self.count=0
        self.signin=0
        self.mainCount=mainCount.Count()
        if kivy.platform=='android':
            self.model=cv2.createLBPHFaceRecognizer()
        else:
            #self.model=cv2.createEigenFaceRecognizer()
            self.model=cv2.createLBPHFaceRecognizer()
        self.model.load('./model.h5')
        super(Camera, self).__init__(**kwargs)
        if self.index == -1:
            self.index = 0
        on_index = self._on_index
        fbind = self.fbind
        fbind('index', on_index)
        fbind('resolution', on_index)
        on_index()
    def _camera_loaded(self, *largs):
        if kivy.platform=='android':
            self.texture = Texture.create(size=self.resolution,colorfmt='rgb')
            self.texture_size = list(self.texture.size)
        else:
            self.texture = Texture.create(size=self.resolution,colorfmt='bgr')
            self.texture_size = list(self.texture.size)
            #super(CV2mera, self)._camera_loaded()


    def on_tex(self, *l):
        if kivy.platform=='android':
            buf = self._camera.grab_frame()
            if not buf:
                return
            self.frame = self._camera.decode_frame(buf)
            buf = self.process_frame()
            self.texture.blit_buffer(buf, colorfmt='rgb', bufferfmt='ubyte')
        else:
            ret, self.frame = self._camera._device.read()
            buf = self.process_frame()
            self.texture.blit_buffer(buf, colorfmt='bgr', bufferfmt='ubyte')
        super(CV2mera, self).on_tex(*l)
    def changeIndex(self):
        if self.index==0:
            self.index=1
        else:
            self.index=0
        on_index = self._on_index
        on_index()

    def sure(self):
        self.username=mainapp.textin.ids.tin.text
        print(mainapp.textin.ids.tin.text)


    '''def pretreat(self,roi):
        eyes = eye_cascade.detectMultiScale(roi, 1.03, 5, 0, (40,40))
        if len(eyes)!=2:
            return
        eyex=[]
        eyey=[]
        for i,(ex,ey,ew,eh) in enumerate(eyes):
            cv2.rectangle(self.frame,(x+ex,y+ey),(x+ex+ew,y+ey+eh),(0,255,0),2)
            eyex[i]=ex
            eyey[i]=ey
        eyexmid=(eyex[0]+eyex[1])*0.5
        eyeymid=(eyey[0]+eyey[1])*0.5
        dx=eyex[1]>eyex[0]?(eyex[1]-eyex[0]):(eyex[0]-eyex[1])
        dy=eyey[1]>eyey[0]?(eyey[1]-eyey[0]):(eyey[0]-eyey[1])
        len=math.sqrt(dx*dx+dy*dy)
        angle=math.atan2(dx,dy)*180/cv2.CV_PI
        scale=0.68*70/len
        M=cv2.getRotationMatrix2D((eyexmid,eyeymid),angle,scale)
        ex=70*0.5-eyexmid
        ey=70*0.14-eyeymid
        M.at<double>(0,2)+=ex
        M.at<double>(1,2)+=ey
        roi=cv2.warpAffine(roi,M,(70,70))
        roi=cv2.equalizeHist(roi)
        roi=cv2.bilateralFilter(roi,0,20,2)
        img=
        cv2.ellipse(img,(70*0.5,70*0.4),(cv2.cvRound(70*0.5),cv2.cvRound(70*0.8),0,0,360,Scalar(0),CV_FILLED)
        roi.setTo(Scalar(128),img'''
    def detect(self):

        if kivy.platform=='android':

            gray1=cv2.cvtColor(self.frame,cv2.COLOR_BGR2GRAY)
            clahe=cv2.createCLAHE(clipLimit=2.0,tileGridSize=(8,8))
            gray=clahe.apply(gray1)
            #print('start',time.strftime('%Y%m%d%H%M%S',time.localtime(time.time())))
            faces = face_cascade_improved.detectMultiScale(gray,scaleFactor = 1.2,minNeighbors = 4,minSize = (100,100),flags = cv2.cv.CV_HAAR_SCALE_IMAGE)
            #faces = face_cascade.detectMultiScale(gray, 1.1,3)
            #print('face',faces)
            #print('end',time.strftime('%Y%m%d%H%M%S',time.localtime(time.time())))
            if len(faces)==0:
                #print('prostart',time.strftime('%Y%m%d%H%M%S',time.localtime(time.time())))
                faces = face_cascade_pro.detectMultiScale(gray,scaleFactor = 1.3,minNeighbors = 3,minSize = (100,100),flags = cv2.cv.CV_HAAR_SCALE_IMAGE)
                #print('proend',time.strftime('%Y%m%d%H%M%S',time.localtime(time.time())))
            #if len(faces)==0:
                #print('imstart',time.strftime('%Y%m%d%H%M%S',time.localtime(time.time())))
                #faces = face_cascade_improved.detectMultiScale(gray,scaleFactor = 1.3,minNeighbors = 3,minSize = (100,100),flags = cv2.cv.CV_HAAR_SCALE_IMAGE)
                #print('imend',time.strftime('%Y%m%d%H%M%S',time.localtime(time.time())))
        else:
            gray1=cv2.cvtColor(self.frame,cv2.COLOR_BGR2GRAY)
            clahe=cv2.createCLAHE(clipLimit=2.0,tileGridSize=(8,8))
            gray=clahe.apply(gray1)
            faces = face_cascade_improved.detectMultiScale(gray,scaleFactor = 1.2,minNeighbors = 4,minSize = (100,100),flags = cv2.cv.CV_HAAR_SCALE_IMAGE)

        for (x,y,w,h) in faces:
            if kivy.platform=='android':
                cv2.rectangle(self.frame,(x,y),(x+w,y+h),(155,155,255),2)
                cv2.putText(self.frame,'>3<',(x-30,y),cv2.FONT_HERSHEY_SIMPLEX,1,(155,155,255),2)
                cv2.putText(self.frame,'>3<',(x+w-30,y+h+20),cv2.FONT_HERSHEY_SIMPLEX,1,(155,155,255),2)
            else:
                cv2.rectangle(self.frame,(x,y),(x+w,y+h),(255,155,155),2)
                cv2.putText(self.frame,'>3<',(x-30,y),cv2.FONT_HERSHEY_SIMPLEX,1,(255,155,155),2)
                cv2.putText(self.frame,'>3<',(x+w-30,y+h+20),cv2.FONT_HERSHEY_SIMPLEX,1,(255,155,155),2)
                #cv2.putText(self.frame,'>3<',(x+w-30,y),cv2.FONT_HERSHEY_SIMPLEX,1,(255,155,155),2)
                #cv2.putText(self.frame,'>3<',(x-30,y+h+25),cv2.FONT_HERSHEY_SIMPLEX,1,(255,155,155),2)
            if self.save:
                if self.count==0:
                    try:
                        import json
                        data = {}
                        with open('datadir.json') as json_file:
                            data = json.load(json_file)
                            self.datadir = data[0]['datadir']
                        name=[self.username]
                        import csv
                        with open('name.csv','ab') as csvfile:
                            writer = csv.writer(csvfile,delimiter=',')
                            writer.writerow(name)
                            print name
                            print name[0]
                        self.mainCount.insertName(name[0])
                    except Exception as err:
                        print(err)
                f = cv2.resize(gray[y:y+h, x:x+w], (92, 112))
                if not os.path.exists('./data/s%s' %self.datadir):
                    os.makedirs('./data/s%s' %self.datadir)
                cv2.imwrite('./data/s%s/%s.pgm' %(self.datadir,str(self.count)), f)
                print self.count
                time.sleep(2)
                self.count += 1
            self.signin+=1
            if self.signin>10:
                self.signin=0
                roi = gray[x:x+w, y:y+h]
                self.sign=False
                try:
                    roi = cv2.resize(roi, (92, 112), interpolation=cv2.INTER_LINEAR)
                    params = self.model.predict(roi)
                    print "Label: %s, Confidence: %.2f" % (params[0], params[1])
                    import csv
                    with open('name.csv','rb') as csvfile:
                        reader = csv.reader(csvfile)
                        for i,line in enumerate(reader):
                            if i==params[0]:

                                name=line[0]
                                print name
                    if kivy.platform=='android':
                        if params[1]<=85:
                            cv2.putText(self.frame, '%s sign in successfully'%name, (x, y-55 ), cv2.FONT_HERSHEY_SIMPLEX, 1, (155,155,255), 2)
                            cv2.putText(self.frame, 'Confidence: %.2f'%params[1], (x, y-25), cv2.FONT_HERSHEY_SIMPLEX, 1, (155,155,255), 2)
                            self.mainCount.insertWorkTime(params[0]+1)
                        else:
                            cv2.putText(self.frame, 'nobody,fail to sign in', (x, y-25 ), cv2.FONT_HERSHEY_SIMPLEX, 1, (155,155,255), 2)

                    else:
                        if params[1]<=85:
                            cv2.putText(self.frame, '%s sign in successfully'%name, (x, y-55 ), cv2.FONT_HERSHEY_SIMPLEX, 1, (255,155,155), 2)
                            cv2.putText(self.frame, 'Confidence: %.2f'%params[1], (x, y-25), cv2.FONT_HERSHEY_SIMPLEX, 1, (255,155,155), 2)
                            self.mainCount.insertWorkTime(params[0]+1)
                        else:
                            cv2.putText(self.frame, 'nobody,fail to sign in', (x, y-25 ), cv2.FONT_HERSHEY_SIMPLEX, 1, (255,155,155), 2)

                except Exception as err:
                    print(err)
                    continue
            #roi_gray = gray[y:y+h, x:x+w]
            #eyes = eye_cascade.detectMultiScale(roi_gray, 1.03, 5, 0, (40,40))
            #print ("eyes",len(eyes))
            #for(ex,ey,ew,eh) in eyes:
                #cv2.rectangle(self.frame,(x+ex,y+ey),(x+ex+ew,y+ey+eh),(0,255,0),2)
        if self.count>10:
            if kivy.platform=='android':
                cv2.putText(self.frame, 'save successfully', (100, 100), cv2.FONT_HERSHEY_SIMPLEX, 1, (155,155,255), 2)
            else:
                cv2.putText(self.frame, 'save successfully', (100, 100), cv2.FONT_HERSHEY_SIMPLEX, 1, (255,155,155), 2)
            self.save=False
            self.count = 0
            try:
                import json

                with open('datadir.json','w') as json_file:
                    #print('datadir',self.datadir)
                    self.datadir=str(int(self.datadir)+1)
                    #print('datadir',self.datadir)
                    data = [{'datadir':self.datadir}]
                    json_file.write(json.dumps(data))
            except Exception as err:
                print(err)
        if self.train:
            [X,y] = self.read_images()
            y = np.asarray(y, dtype=np.int32)
            if kivy.platform=='android':
                self.model=cv2.createLBPHFaceRecognizer()
                self.model.train(np.asarray(X), np.asarray(y))
                self.model.save('./model.h5')
                cv2.putText(self.frame, 'train successfully', (100, 100), cv2.FONT_HERSHEY_SIMPLEX, 1, (155,155,255), 2)
            else:
                #self.model=cv2.createEigenFaceRecognizer()
                self.model=cv2.createLBPHFaceRecognizer()
                self.model.train(np.asarray(X), np.asarray(y))
                self.model.save('./model.h5')
                cv2.putText(self.frame, 'train successfully', (100, 100), cv2.FONT_HERSHEY_SIMPLEX, 1, (255,155,155), 2)
            self.train=False
    def process_frame(self):
        if kivy.platform=='android':
            r,g,b=cv2.split(self.frame)
            self.frame=cv2.merge((b,g,r))

            rows,cols,channel=self.frame.shape
            if self.index==1:
                M=cv2.getRotationMatrix2D((cols/2,rows/2),90,1)
                self.frame=cv2.warpAffine(self.frame,M,(cols,rows))
                self.frame=cv2.flip(self.frame,1)
                self.detect()
                self.frame=cv2.flip(self.frame,0)
            else:
                M=cv2.getRotationMatrix2D((cols/2,rows/2),270,1)
                self.frame=cv2.warpAffine(self.frame,M,(cols,rows))
                self.detect()
                self.frame=cv2.flip(self.frame,0)
        else:
            self.frame=cv2.flip(self.frame,1)
            self.detect()
            self.frame=cv2.flip(self.frame,0)
            #rows,cols,channel=self.frame.shape
            #M=cv2.getRotationMatrix2D((cols/2,rows/2),180,1)
            #self.frame=cv2.warpAffine(self.frame,M,(cols,rows))
            #self.frame=cv2.flip(self.frame,1)
            #print('frame',self.frame)
            #print('tostring',self.frame.tostring())
        return self.frame.tostring()
    def read_images(self):
        c = 0
        X,y = [], []
        for dirname, dirnames, filenames in os.walk('./data' ):
            for subdirname in dirnames:
                subject_path = os.path.join(dirname, subdirname)
                for filename in os.listdir(subject_path):
                    try:
                        if (filename == ".directory"):
                            continue
                        filepath = os.path.join(subject_path, filename)
                        im = cv2.imread(os.path.join(subject_path, filename), cv2.IMREAD_GRAYSCALE)
                        if (im is None):
                            print "image " + filepath + " is none"
                        else:
                            print filepath
                        X.append(np.asarray(im, dtype=np.uint8))
                        y.append(c)
                    except IOError, (errno, strerror):
                        print "I/O error({0}): {1}".format(errno, strerror)
                    except:
                        print "Unexpected error:", sys.exc_info()[0]
                        raise
                print c
                c = c+1
        print y
        return [X,y]

class FirstLayout(BoxLayout):
    pass

class OpenLayout(BoxLayout):
    pass

class TextLayout(BoxLayout):
    pass

class MainApp(App):
    def build(self):

        self.root = Carousel()
        try:
            #self.first=FirstLayout()
            self.openface=OpenLayout()
            self.textin=TextLayout()
            self.table = TableView(size=(500,320),
                pos_hint={'x':0.1, 'center_y':.5})
            #root.add_widget(self.first)
            self.tableCount()
            self.root.add_widget(self.openface)
            self.root.add_widget(self.textin)
            self.root.add_widget(self.table)
        except Exception,e:
            traceback.print_exc()
        return self.root
    #def on_start(self):
        #Clock.schedule_once(self.openface.ids.camera.detect,5)


    def on_start(self):
        Clock.schedule_interval(self.insertCount,1*60*60*24*30)

    def insertCount(self,nap):
        self.root.remove_widget(self.table)
        self.table = TableView(size=(500,320),
                pos_hint={'x':0.1, 'center_y':.5})
        connection = sqlite3.connect('attendence.db')
        cur=connection.cursor()
        month=time.strftime('%Y%m%d%H%M%S',time.localtime(time.time()))[0:6]
        if month[4:6]=='01':
            year=int(month[0:4]-1)
            month=str(year)+'12'
        else:
            mon=int(month[4:6])-1
            if mon<10:
                month=month[0:4]+'0'+str(mon)
            else:
                month=month[0:4]+str(mon)
        print('month',month)
        maxtime=time.strftime('%Y-%m-%d %H:%M:%S',time.localtime(time.time()))[0:8]+'01 00:00:00'
        print('maxtime',maxtime)
        cur.execute("select w.*,lbo.businesstriptime,lbo.leavetime,lbo.overttime from (select employment_ID,sum(round((julianday(endtime)-julianday(starttime))*24)) as worktime from work \
                    where starttime<? or endtime<? group by employment_ID) w \
                    left join (select l.* ,bo.businesstriptime,bo.overttime from \
                    (select employment_ID,sum(round((julianday(endtime)-julianday(starttime))*24)) as leavetime from leave \
                    where starttime<? or endtime<? group by employment_ID) l \
                    left join (select b.* ,o.overttime from (select employment_ID,sum(round((julianday(endtime)-julianday(starttime))*24)) as businesstriptime \
                    from businesstrip where starttime<? or endtime<? group by employment_ID) b \
                    left join (select employment_ID,sum(round((julianday(endtime)-julianday(starttime))*24)) as overttime from overtime \
                    where starttime<? or endtime<? group by employment_ID) o \
                    on b.employment_ID=o.employment_ID) bo on l.employment_ID=bo.employment_ID) lbo on w.employment_ID=lbo.employment_ID",(maxtime,maxtime,maxtime,maxtime,maxtime,maxtime,maxtime,maxtime,))
        for row in cur:
            cur.execute("insert into count(employment_ID,work,businesstrip,leave,overtime,month) values(?,?,?,?,?,?)",(row[0],row[1],row[2],row[3],row[4],month,))
        connection.commit()
        connection.close()
        self.tableCount()
        self.root.add_widget(self.table)

    def tableCount(self):
        # columns
        self.table.add_column(TableColumn("employment", key="1", hint_text='0'))
        self.table.add_column(TableColumn("work", key="2", hint_text='0'))
        self.table.add_column(TableColumn("businesstrip", key="3", hint_text='0'))
        self.table.add_column(TableColumn("leave", key="4", hint_text='0'))
        self.table.add_column(TableColumn("overtime", key="5", hint_text='0'))
        # content
        connection = sqlite3.connect('attendence.db')
        cur=connection.cursor()
        month=time.strftime('%Y%m%d%H%M%S',time.localtime(time.time()))[0:6]
        print('month',month)
        if month[4:6]=='01':
            year=int(month[0:4]-1)
            month=str(year)+'12'
        else:
            mon=int(month[4:6])-1
            if mon<10:
                month=month[0:4]+'0'+str(mon)
            else:
                month=month[0:4]+str(mon)
        print('month',month)
        cur.execute("select e.username,c.work,c.businesstrip,c.leave,c.overtime from employment e left join count c on e.ID=c.employment_ID where month=?",(month,))
        for r in cur:
            row = {'1': str(r[0]), '2': str(r[1]),'3': str(r[2]), '4': str(r[3]),'5':r[4]}
            self.table.add_row(row)
        connection.commit()
        connection.close()
Window.clearcolor = get_color_from_hex('#001015')
mainapp= MainApp()
mainapp.run()
