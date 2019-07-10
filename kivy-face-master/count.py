import sqlite3
import time
class Count():
    def insertName(self,name):
        connection = sqlite3.connect('attendence.db')
        cur=connection.cursor()
        cur.execute("insert into employment (username) values (?)",(name,))
        connection.commit()
        connection.close()

    def insertWorkTime(self,employid):
        connection = sqlite3.connect('attendence.db')
        c = connection.cursor()
        '''cursor = c.execute("SELECT id from employment where ID=?",(name,))
        for row in cursor:
            employid=row[0]
            print "ID = ", row[0]'''
        #print('time',time.strftime('%Y%m%d%H%M%S',time.localtime(time.time()))[8:10])
        if time.strftime('%Y%m%d%H%M%S',time.localtime(time.time()))[8:10]<'12':
            c.execute('insert into work (employment_ID,starttime) values (?,?) ',(employid,time.strftime('%Y-%m-%d %H:%M:%S',time.localtime(time.time()))),)
        else:
            #print('mintime',time.strftime('%Y%m%d%H%M%S',time.localtime(time.time()))[0:8]+'000000')
            cursor = c.execute("SELECT id from work where employment_ID=? and starttime>?",(employid,time.strftime('%Y-%m-%d %H:%M:%S',time.localtime(time.time()))[0:11]+'00:00:00',))
            #print('cursor',cursor)
            count=0
            for row in cursor:
                workid=row[0]
                #print('workid',workid)
                c.execute('update work set endtime=? where ID=?',(time.strftime('%Y-%m-%d %H:%M:%S',time.localtime(time.time())),workid),)
                count+=1
            if count==0:
                c.execute('insert into work (employment_ID,endtime) values (?,?) ',(employid,time.strftime('%Y-%m-%d %H:%M:%S',time.localtime(time.time()))),)
        connection.commit()
        connection.close()

    def insertCount(self):
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
        maxtime=time.strftime('%Y-%m-%d %H:%M:%S',time.localtime(time.time()))
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
            cur.execute("insert into count(employment_ID,work,businesstrip,leave,overtime,month) values(?,?,?,?,?)",(row[0],row[1],row[2],row[3],month,))
        connection.commit()
        connection.close()
#count=Count()
#count.insertName("abc")
#count.insertWorkTime("abc")

