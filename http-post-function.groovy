import com.atlassian.jira.ComponentManager
import com.atlassian.jira.issue.Issue
import org.apache.log4j.Category
import com.atlassian.jira.issue.Issue;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.*;
import com.atlassian.jira.issue.label.LabelManager
import com.opensymphony.user.User

log = Category.getInstance("com.onresolve.jira.groovy.example.SendEmailOnBlocker")

log.error("Testing");


Issue issue  = issue

log.error("Issue: " + issue.getKey());
URL url = new URL("http://localhost:6230/print?issue=" + issue.getKey() );
HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
httpCon.connect();
log.error("Connected");

InputStreamReader input = new InputStreamReader((InputStream) httpCon.getContent());
BufferedReader buff = new BufferedReader(input);
String line,key,header;
int i=0; //displays headers too
    StringBuffer text=new StringBuffer();

while(1) {
    key=httpCon.getHeaderFieldKey(i);
    header=httpCon.getHeaderField(i);
    if ( key==null ) {
        key="";
    }else{
        key=key+": ";
    }
    if(header!=null) {
        text.append(key+header+"\n");
    } else {
        break;
    }
    i++;
}

while(1) {
    line=buff.readLine();

    if (line == null) {
        break;
    }

    text.append(line+"\n");
}

log.error(text)


// Add a KANBAN label to the issue

ComponentManager componentManager = ComponentManager.getInstance();
LabelManager lm = componentManager.getComponent(LabelManager.class)
User remoteUser = componentManager.getJiraAuthenticationContext().getUser();

// XXX: Should perhaps check for existance before adding?
lm.addLabel(remoteUser, issue.getId(),"kanban", false)

