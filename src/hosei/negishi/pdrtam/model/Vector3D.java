package hosei.negishi.pdrtam.model;

/**
 * 3次元ベクトルクラス
 */
public class Vector3D implements Cloneable {	
	public static enum VectorTYPE {
		X, Y, Z
	}
	public final float x;
	public final float y;
	public final float z;
	
	/** ベクトルオブジェクトの構築(全成分0) */
	public Vector3D(){
		this.x = 0f;
		this.y = 0f;
		this.z = 0f;
	}
	
	/** ベクトルオブジェクトの構築 */
	public Vector3D(float x, float y, float z){
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public Vector3D(double x, double y, double z){
		this.x = (float)x;
		this.y = (float)y;
		this.z = (float)z;
	}
	
	/** ベクトルオブジェクトの構築 */
	public Vector3D(Vector3D v){
		this.x = v.x;
		this.y = v.y;
		this.z = v.z;
	}

//	public void set(Vector3D v){
//		this.x = v.x;
//		this.y = v.y;
//		this.z = v.z;
//	}
//	
//	public void set(float x, float y, float z){
//		this.x = x;
//		this.y = y;
//		this.z = z;
//	}
	
	/** 正規化ベクトルを取得する */
	public Vector3D normalize(){
		double length  = length();
		double tmpX = this.x / length;
		double tmpY = this.y / length;
		double tmpZ = this.z / length;
		return new Vector3D(tmpX, tmpY, tmpZ);
	}
	
	/** ベクトルの大きさを求める */
	public double length(){
		return Math.sqrt(x * x + y * y + z * z);
	}
	
	public VectorTYPE getMax() {
		if (x > y) {
			if (x > z) return VectorTYPE.X;
			else return VectorTYPE.Z;
		} else {
			if (y > z) return VectorTYPE.Y;
			else return VectorTYPE.Z;
		}
	}
	
	/** スカラー倍 */
//	public void mult(float d){
//		x *= d;
//		y *= d;
//		z *= d;
//	}
	
	/** スカラー倍 */
	public Vector3D multCreate(float d){
		return new Vector3D(x *d, y * d, z * d);
	}
	
	/** ベクトルの足し算 */
//	public void plus(Vector3D v){
//		x += v.x;
//		y += v.y;
//		z += v.z;
//	}
	
	/** ベクトルの足し算 */
	public Vector3D plusCreate(Vector3D v){
		return new Vector3D(x + v.x, y + v.y, z + v.z);
	}
	
	/** ベクトルの引き算 */
	public Vector3D sub(Vector3D v){
		return new Vector3D(x - v.x, y - v.y, z - v.z);
	}
	
	/** 内積を求める */
	public double innerProduct(Vector3D v){
		return x * v.x + y * v.y + z * v.z;
	}
	
	/** 外積を求める */
	public Vector3D exteriorProduct(Vector3D v){
		float tmpX = y * v.z - v.y * z;
		float tmpY = z * v.x - v.z * x;
		float tmpZ = x * v.y - v.x * y;
		return new Vector3D(tmpX, tmpY, tmpZ);
	}
	
	/** ベクトルの要素を配列として返す */
	public double[] getElements(){
		double[] elem = new double[3];
		elem[0] = x;
		elem[1] = y;
		elem[2] = z;
		return elem;
	}

	/**
	 * 回転
	 * @param ax x軸周りの回転角
	 * @param ay y軸周りの回転角
	 * @param az z軸周りの回転角
	 * @return ベクトル
	 */
	public Vector3D rotate(double ax, double ay, double az){
		return Matrix4D.rotateMatrix(ax, ay, az).mult(this);
	}
	
	/**
	 * 回転
	 * @param v 回転ベクトル
	 * @return 回転後のベクトル
	 */
	public Vector3D rotate(Vector3D v){
		return Matrix4D.rotateMatrix(v.x, v.y, v.z).mult(this);
	}
	
	@Override
	public Vector3D clone(){
		Vector3D clone = null;
		try {
			clone = (Vector3D) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return clone;
	}
	
	@Override
	public String toString(){
		return x + ", " + y + ", " + z;
	}
	
}
